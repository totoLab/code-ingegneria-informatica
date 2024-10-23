package tracce.t20230711.parte2;

import java.io.*;
import java.net.*;
import java.util.*;

// 14:08
public class Server {

    Map<String, Map<String, TreeSet<Offerta>>> offerte = Collections.synchronizedMap(new HashMap<>());
    Map<String, List<InetAddress>> registrati = Collections.synchronizedMap(new HashMap<>());

    void ricezione() {
        new Thread(() -> {
            ServerSocket ricezioneOfferte = null;
            try {
                ricezioneOfferte = new ServerSocket(3000);
                while (true) {
                    Socket negozio = ricezioneOfferte.accept();
                    ObjectInputStream ois = new ObjectInputStream(negozio.getInputStream());
                    Offerta offerta = (Offerta) ois.readObject();

                    if (!offerte.containsKey(offerta.nazione)) offerte.put(offerta.nazione, Collections.synchronizedMap(new HashMap<>()));
                    Map<String, TreeSet<Offerta>> offerteNazione = offerte.get(offerta.nazione);
                    TreeSet<Offerta> offerteNazioneCodice = offerteNazione.get(offerta.codice);
                    if (offerta.quantita == 0) {
                        for (Offerta offertaIt : offerteNazioneCodice) {
                            if (offerta.piva.equals(offertaIt.piva)) {
                                offerteNazioneCodice.remove(offertaIt);
                            }
                        }
                    } else {
                        if (offerta.prezzo < getOffertaMigliore(offerta.nazione, offerta.codice).prezzo) {
                            broadcast(offerta);
                            broadcastClienti(offerta);
                        }
                        offerteNazioneCodice.add(offerta);
                    }
                    negozio.close();
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
        }).start();
    }

    Offerta getOffertaMigliore(String nazione, String codice) {
        TreeSet<Offerta> offertePossibili = offerte.get(nazione).get(codice);
        return offertePossibili.isEmpty() ? null : offertePossibili.getFirst();
    }

    void broadcast(Offerta offerta) {
        new Thread(() -> {
            String data = offerta.codice + "#" + offerta.prezzo + "#" + offerta.piva + "#" + offerta.nazione;
            MulticastSocket ms = null;
            try {
                ms = new MulticastSocket();
                InetAddress ip = InetAddress.getByName("230.0.0.1");
                byte[] buf = data.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 5000);
                ms.send(packet);
            } catch (IOException e) {

            }
        }).start();
    }

    void gestioneClienti() {
        new Thread(() -> {
            ServerSocket gestioneClienti = null;
            try {
                gestioneClienti = new ServerSocket(4000);
                while (true) {
                    Socket cliente = gestioneClienti.accept();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    String line = bf.readLine();
                    String[] parts = line.split("#");
                    String codice = parts[0];
                    String nazione = parts[1];
                    boolean registrazione = parts[2].toLowerCase().contains("true");
                    if (registrazione) {
                        String codiceNazione = codice + "#" + nazione;
                        if (!registrati.containsKey(codiceNazione)) registrati.put(codiceNazione,new LinkedList<>());
                        registrati.get(codiceNazione).add(cliente.getInetAddress());
                    }

                    Offerta offerta = getOffertaMigliore(nazione, codice);
                    PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);
                    if (offerta == null) {
                        pw.println("");
                    }
                    pw.println(offerta);

                }
            } catch (IOException e) {

            }
        }).start();
    }

    void broadcastClienti(Offerta offerta) {
        new Thread(() -> {
            String data = offerta.codice + "#" + offerta.prezzo + "#" + offerta.piva + "#" + offerta.nazione;
            DatagramSocket ms = null;
            try {
                ms = new DatagramSocket();
                for (String codiceNazione : registrati.keySet()) {
                    String[] parts = codiceNazione.split("#");
                    String codice = parts[0];
                    String nazione = parts[1];
                    if (offerta.nazione.equals(nazione) && offerta.codice.equals(codice)) {
                        for (InetAddress ip : registrati.get(codiceNazione)) {
                            byte[] buf = data.getBytes();
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 6000);
                            ms.send(packet);
                        }
                    }
                }
            } catch (IOException e) {

            }
        }).start();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.ricezione();
        server.gestioneClienti();
    }

}
