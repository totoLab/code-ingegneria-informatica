package tracce.t20240624.parte2;

import utils.Logging;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    List<String> utenti;
    List<String> fornitoriAffidabili;
    Map<Integer, Partita> partite = new ConcurrentHashMap<>();

    final InetAddress HOST;
    final String HOSTNAME = "localhost";
    final static int USERS_PORT = 1234;
    final static int SUPPLIERS_PORT = 2345;
    final static int NOTIFICATIONS_PORT = 3456;
    final String MULTICAST_HOSTNAME = "230.0.0.1";
    final InetAddress MULTICAST_HOST;
    private MulticastSocket ms;

    Server(List<String> fornitoriAffidabili) throws IOException {
        this.HOST = InetAddress.getByName(HOSTNAME);
        this.MULTICAST_HOST = InetAddress.getByName(MULTICAST_HOSTNAME);
        this.ms = new MulticastSocket(NOTIFICATIONS_PORT);
        this.utenti = new CopyOnWriteArrayList<>();
        this.utenti.add("antolab");
        this.utenti.add("admin");

        this.fornitoriAffidabili = new CopyOnWriteArrayList<>(fornitoriAffidabili);
        new Thread(() -> {
            userHandling();
        }).start();
        new Thread(() -> {
            fornitoriHandling();
        }).start();
    }

    void userHandling() {
        ServerSocket server;
        try {
            server = new ServerSocket(USERS_PORT);
            while (true) {
                Socket client = server.accept();
                new UserHandler(client).start();
            }
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error during users' server init", null, null, e);
        }
    }


    class UserHandler extends Thread {

        Socket client;

        UserHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                LocalTime currentTime = LocalTime.now();
                if (
                        currentTime.isBefore(LocalTime.of(1, 0)) ||
                        currentTime.isAfter(LocalTime.of(18, 0))) {
                    out.println("500 il server non è attivo");
                } else {
                    out.println("Benvenuto!");
                }
                System.out.println("Instaurata connessione con utente");
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line = in.readLine();
                String[] parts = line.split("-");
                String username = parts[0].trim();
                String nazionale = parts[1].trim();
                String ora = parts[2].trim();
                LocalTime time = null;
                try {
                    time = LocalTime.parse(ora);
                } catch (Exception e) {
                    time = LocalTime.of(18, 0);
                }
                System.out.println("Elaborazione richiesta " + line);
                LocalTime finalTime = time;
                List<Partita> partiteConSquadra = partite.values().stream()
                        .filter(
                            o -> o.gioca(nazionale) &&
                            o.getOrario().equals(finalTime))
                        .toList();
                if (utenti.contains(username) && !partiteConSquadra.isEmpty()) {
                    out.println(partiteConSquadra.getFirst().toString());
                } else {
                    out.println("500 Impossibile soddisfare la richiesta");
                }

            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Error while communicating with user", null, null, e);
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    Logging.print(Logging.Type.ERROR, "Couldn't shutdown connection with client gracefully", null, null, e);
                }
            }
        }
    }

    void fornitoriHandling() {
        ServerSocket server;
        try {
            server = new ServerSocket(SUPPLIERS_PORT);
            while (true) {
                Socket fornitore = server.accept();
                new FornitoreHandler(fornitore).start();
            }
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error during suppliers' server init", null, null, e);
        }
    }

    class FornitoreHandler extends Thread {

        Socket fornitore;

        FornitoreHandler(Socket fornitore) {
            this.fornitore = fornitore;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(fornitore.getInputStream());
                MessaggioFornitore aggiornamentoPartite = (MessaggioFornitore) in.readObject();
                Logging.print(Logging.Type.INFO, "Received update...", null, null, null);
                if (fornitoriAffidabili.contains(aggiornamentoPartite.nome)) {
                    for (Partita partita : aggiornamentoPartite.partite) {
                        Partita overwritten = partite.put(partita.getId(), partita);
                        if (!partita.equals(overwritten)) {
                            Logging.print(Logging.Type.INFO, "Updating info with " + partita, null, null, null);
                            sendNotification(String.format(
                                    "Partita %d modificata: nuovi dettagli %s",
                                    partita.getId(), partita.toString()
                            ));
                        }
                    }
                }

            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Error while communicating with supplier", null, null, e);
            } catch (ClassNotFoundException e) {
                Logging.print(Logging.Type.ERROR, "Error while deserializing sent object", null, null, e);
            }
        }
    }

    void sendNotification(String content) {
        try {
            byte[] buf = content.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, MULTICAST_HOST, NOTIFICATIONS_PORT);
            ms.send(packet);
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error during notifications' server init", null, null, e);
        }
    }

    public static void main(String[] args) {
        List<String> affidabili = List.of("A", "B", "C");
        try {
            Server server = new Server(affidabili);
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error during server's init", null, null, e);
        }


    }

}
