package tracce.t20240314;

import java.io.*;
import java.net.*;
import java.util.*;

import static utils.Logging.Type;
import static utils.Logging.print;

public class Server {

    static int NUMERO_PROGRESSIVO = 0;
    static int NOTIF_PORT = 4000;
    private Map<Integer, List<StatoSensore>> stati = Collections.synchronizedMap(new HashMap<>());
    private List<UtenteNotifica> registrati = Collections.synchronizedList(new ArrayList<>());

    private class UtenteNotifica {
        int id;
        InetAddress ip;

        public UtenteNotifica(int id, InetAddress ip) {
            this.id = id;
            this.ip = ip;
        }
    }

    private void ricezione() {
        ServerSocket serverRichieste = null;
        try {
            serverRichieste = new ServerSocket(3000);
            while (true) {
                Socket sensore = serverRichieste.accept();

                new Thread(() -> {
                    try {
                        PrintWriter pw = new PrintWriter(sensore.getOutputStream(), true);
                        ObjectInputStream ois = new ObjectInputStream(sensore.getInputStream());
                        StatoSensore stato = (StatoSensore) ois.readObject();
                        boolean orarioOk = true; // 8:00 < now < 13:00

                        if (orarioOk) {
                            if (checkUmidita(stato.getId(), stato.getTemperatura()) && checkTemperatura(stato.getTemperatura())) {
                                pw.println("ACCETTATA: numero progressivo richiesta " + NUMERO_PROGRESSIVO);
                                stato.setNumeroProgressivo(NUMERO_PROGRESSIVO++);

                                if (!stati.containsKey(stato.getId())) {
                                    stati.put(stato.getId(), new ArrayList<>());
                                }
                                stati.get(stato.getId()).add(stato);

                                sendNotification(stato);
                            }
                        } else {
                            pw.println("RIFIUTATA: tempo scaduto");
                        }
                    } catch (IOException e) {
                        print(Type.ERROR, "Couldn't communicate with sensor", null, this, e);
                    } catch (ClassNotFoundException e) {
                        print(Type.ERROR, "Couldn't cast object correctly", null, this, e);
                    }
                }).start();
            }
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't establish connection with sensor", null, this, e);
        }

    }

    private boolean checkUmidita(int id, int umidita) {
        List<StatoSensore> statiSensore = stati.get(id);
        double somma = 0.0;
        for (StatoSensore s : statiSensore) {
            somma += s.getUmidita();
        }
        double media = somma / statiSensore.size();
        return media * 95/100 <= umidita && umidita <= media * 105/100;
    }

    private boolean checkTemperatura(int temperatura) {
        double somma = 0.0;
        int numeroStati = 0;
        for (int id: stati.keySet()) {
            List<StatoSensore> statiSensore = stati.get(id);

            for (StatoSensore s : statiSensore) {
                somma += s.getUmidita();
                numeroStati++;
            }
        }
        double media = somma / numeroStati;
        return media * 95/100 <= temperatura && temperatura <= media * 105/100;
    }

    private void sendNotification(StatoSensore stato) {
        new Thread(() -> {
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket();
                byte[] buf = new byte[256];
                String msg = "#" + stato.getId() + "#" + stato.getNumeroProgressivo() + "#" + stato.getUmidita() + "," + stato.getTemperatura();
                buf = msg.getBytes();
                for (UtenteNotifica u : registrati) {
                    if (u.id != stato.getId()) {
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, u.ip, NOTIF_PORT);
                        ds.send(packet);
                    }
                }
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't communicate with sensor", null, this, e);
            }
        }).start();
    }

    private void registrazione() {
        new Thread(() -> {
            ServerSocket serverSignup = null;
            try {
                serverSignup = new ServerSocket(NOTIF_PORT);
                while (true) {
                    Socket sensore = serverSignup.accept();
                    PrintWriter pw = new PrintWriter(sensore.getOutputStream(), true);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(sensore.getInputStream()));
                    int id = Integer.parseInt(bf.readLine());

                    registrati.add(new UtenteNotifica(id, sensore.getInetAddress()));
                    pw.println("Registrato correttamente! al servizio di notifica");
                }
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't communicate with sensor", null, this, e);
            }
        }).start();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.registrazione();
        server.ricezione();
    }
}
