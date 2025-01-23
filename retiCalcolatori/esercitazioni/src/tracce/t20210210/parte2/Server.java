package tracce.t20210210.parte2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Server {

    static final int MULTICAST_PORT = 6000;
    InetAddress msGroup;
    static final int RICEZIONE_PORT = 3000;
    static final int QUERY_PORT = 4000;
    MulticastSocket ms = null;
    Semaphore network_mutex = new Semaphore(1);
    Semaphore mutex = new Semaphore(1);

    Set<Integer> clienti = new ConcurrentHashMap<Integer, Integer>().keySet();
    Map<Ordine, LocalDate> ordini = new ConcurrentHashMap<>();
    Map<Integer, Integer> prodotti = new ConcurrentHashMap<>();

    Server() {
        try {
            msGroup = InetAddress.getByName("230.0.0.1");
            ms = new MulticastSocket(MULTICAST_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        handleOrdini();
        handleQuery();
    }

    void handleOrdini() {
        new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(RICEZIONE_PORT);
                while (true) {
                    Socket client = server.accept();
                    new ClientHandler(client).start();
                }

            } catch (IOException e) {

            } finally {
                try {
                    if (server != null) {
                        server.close();
                    }
                } catch (IOException e) {

                }
            }
        }).start();
    }

    int valutazioneOrdine(Ordine ordine) {
        if (!clienti.contains(ordine.idAcquirente)) {
            return 0;
        } else if (prodotti.containsKey(ordine.id) && prodotti.get(ordine.id) < ordine.quantita) {
            comunicazioneNonDisponibilita("Prodotto dell'agente " + ordine.id + " non disponibile");
            return -1;
        }
        prodotti.put(ordine.id, prodotti.get(ordine.id) - ordine.quantita);
        ordini.put(ordine, LocalDate.now());
        return 1;
    }

    class ClientHandler extends Thread {

        private Socket client;

        ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(client.getInputStream());
                Ordine ordine = (Ordine) ois.readObject();

                mutex.acquire();
                int codice = valutazioneOrdine(ordine);
                mutex.release();

                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(codice);

            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            } catch (InterruptedException e) {

            }
        }
    }

    void comunicazioneNonDisponibilita(String prodotto) {
        new Thread(() -> {
            try {
                byte[] buf = prodotto.getBytes();
                network_mutex.acquire();
                try {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, msGroup, MULTICAST_PORT);
                    ms.send(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                network_mutex.release();
            } catch (InterruptedException e) {

            }
        }).start();
    }

    void handleQuery() {
        new Thread(() -> {
            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket(QUERY_PORT);
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                while (true) {
                    try {
                        socket.receive(packet);
                        String query = new String(packet.getData());
                        String[] parts = query.split("-");
                        LocalDate inizio = LocalDate.parse(parts[0]);
                        LocalDate fine = LocalDate.parse(parts[1]);
                        int idAgente = Integer.parseInt(parts[2]);

                        Integer totale = contaOrdini(inizio, fine, idAgente);

                        packet.setData(new String(totale.toString()).getBytes());
                        socket.send(packet);

                    } catch (IOException e) {

                    }
                }
            } catch (IOException e) {

            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }).start();
    }

    int contaOrdini(LocalDate inizio, LocalDate fine, int idAgente) {
        return (int) ordini.keySet().stream().filter(o -> {
            LocalDate ordine = ordini.get(o);
            return o.getId() == idAgente && ordine.isAfter(inizio) && ordine.isBefore(fine);
        }).count();
    }
}
