package tracce.t20220614.parte2;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static utils.Logging.Type;
import static utils.Logging.print;

public class Server {

    class OffertaAzienda {
        Offerta offerta;
        InetAddress ip;
        LocalDate time;

        OffertaAzienda(Offerta offerta, InetAddress ip) {
            this.offerta = offerta;
            this.ip = ip;
            this.time = LocalDate.now();
        }
    }

    static int NUMERO_OFFERTE = 0;
    Map<Integer, OffertaAzienda> offerte = Collections.synchronizedMap(new HashMap<>());

    private void ricezioneOfferte() {
        new Thread(() -> {
            ServerSocket serverOfferte = null;
            try {
                serverOfferte = new ServerSocket(3000);
                while (true) {
                    Socket azienda = serverOfferte.accept();
                    new Thread(() -> {
                        try {
                            ObjectInputStream ois = new ObjectInputStream(azienda.getInputStream());
                            Offerta offerta = (Offerta) ois.readObject();
                            int id = NUMERO_OFFERTE;

                            PrintWriter pw = new PrintWriter(azienda.getOutputStream(), true);
                            pw.println(id);

                            offerte.put(id, new OffertaAzienda(offerta, azienda.getInetAddress()));

                            NUMERO_OFFERTE++;
                            broadcast(id, offerta);
                        } catch (IOException e) {

                        } catch (ClassNotFoundException e) {

                        }
                    }).start();
                }
            } catch (IOException e) {

            }
        }).start();
    }

    private void broadcast(int id, Offerta offerta) {
        final String ip = "230.0.0.1";
        final int PORT = 6000;
        String data = id + "#" + offerta.getSettore() + "#" + offerta.getTipo() + "#" + offerta.getRetribuzione();

        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket();
            byte[] buf = data.getBytes();
            InetAddress hostname = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, hostname, PORT);
            ms.send(packet);
        } catch (IOException e) {

        }
    }

    private void ricezioneCandidature() {
        new Thread(() -> {
            ServerSocket serverCandidature = null;
            try {
                serverCandidature = new ServerSocket(4000);
                while (true) {
                    Socket utente = serverCandidature.accept();
                    new Thread(() -> {
                        try {
                            ObjectInputStream ois = new ObjectInputStream(utente.getInputStream());
                            Candidatura candidatura = (Candidatura) ois.readObject();

                            if (offerte.containsKey(candidatura.getId())) {
                                OffertaAzienda offerta = offerte.get(candidatura.getId());
                                Socket azienda = new Socket(offerta.ip, 5000);
                                ObjectOutputStream oos = new ObjectOutputStream(azienda.getOutputStream());
                                oos.writeObject(candidatura);
                                oos.flush();
                            } else {
                                print(Type.INFO, "Offer expired or never existed", null, this, null);
                            }

                        } catch (IOException e) {

                        } catch (ClassNotFoundException e) {

                        }
                    }).start();
                }
            } catch (IOException e) {

            }
        }).start();
    }

    private void deleteOffers() {
        new Thread(() -> {
            while (true) {
                for (int id : offerte.keySet()) {
                    OffertaAzienda offerta = offerte.get(id);
                    LocalDate today = LocalDate.now();
                    if (offerta.time.plusDays(30).isBefore(today)) {
                        offerte.remove(id);
                    }
                }
                try {
                    TimeUnit.HOURS.sleep(12);
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.ricezioneOfferte();
        s.deleteOffers();
        s.ricezioneCandidature();
    }
}
