package tracce.t20220712.parte2;

import utils.Logging;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private static final Object CLASS_NAME = Server.class;

    Map<Integer, Prodotto> prodotti = new ConcurrentHashMap<>();
    Map<Integer, Offerta> offerte = new ConcurrentHashMap<>();
    Set<Integer> porteUsate = ConcurrentHashMap.newKeySet();
    MulticastSocket ms;
    InetAddress group;
    final static int BROADCAST_PORT = 3000;
    final static int RESI_PORT = 5000;
    Map<Integer, Ordine> ordini = new ConcurrentHashMap<>();

    Server() {
        try {
            this.group = InetAddress.getByName("230.0.0.1");
            ms = new MulticastSocket(BROADCAST_PORT);
            Logging.print(Logging.Type.INFO,
                    "Server initialized successfully",
                    "constructor", CLASS_NAME, null);
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR,
                    "Failed to initialize broadcast service",
                    "constructor", CLASS_NAME, e);
        }
    }

    class Prodotto {
        static int ID_PROGRESSIVO = 0;

        Integer id;
        double prezzo;

        Prodotto(Integer id, double prezzo) {
            this.id = id;
            this.prezzo = prezzo;
        }

        public boolean vendita(int i) { // true offerta, false prezzo originale
            if (offerte.containsKey(id)) {
                Offerta offerta = offerte.get(id);
                return (!offerta.vendita(i));
            }
            return false;
        }
    }

    void aggiungiProdotto(double prezzo) {
        int id = Prodotto.ID_PROGRESSIVO++;
        prodotti.put(id, new Prodotto(id, prezzo));
        Logging.print(Logging.Type.INFO,
                String.format("New product added - ID: %d, Price: %.2f", id, prezzo),
                "aggiungiProdotto", CLASS_NAME, null);
    }

    class Offerta {
        Integer idProdotto;
        double prezzo;
        int pezziRimanenti;
        int sconto;
        LocalTime inizio;

        Offerta(Integer idProdotto, double prezzo, int sconto, int pezziRimanenti) {
            this.idProdotto = idProdotto;
            this.sconto = sconto;
            this.prezzo = prezzo - prezzo * sconto / 100;
            this.pezziRimanenti = pezziRimanenti;
            this.inizio = LocalTime.now();
        }

        boolean vendita(int i) {
            if (this.pezziRimanenti <= 0 || pezziRimanenti < i || inizio.plusHours(3).isBefore(LocalTime.now())) {
                offerte.remove(idProdotto);
                return false;
            }
            this.pezziRimanenti -= i;
            return true;
        }
    }

    class Ordine {
        int id;
        LocalDate data;
        double prezzo;

        Ordine(int id, LocalDate data, double prezzo) {
            this.id = id;
            this.data = data;
            this.prezzo = prezzo;
        }
    }

    void creaOfferte() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(15);
                    Random r = new Random();
                    int idProdotto = 0;
                    while (offerte.containsKey(idProdotto = r.nextInt(prodotti.keySet().size())));
                    int pezzi = r.nextInt(100, 1000);
                    int sconto = r.nextInt(10, 30);
                    Offerta offerta = new Offerta(idProdotto, prodotti.get(idProdotto).prezzo, sconto, pezzi);
                    offerte.put(idProdotto, offerta);
                    Logging.print(Logging.Type.INFO, "New offer generated", null, null, null);

                    int port = 0;
                    while (porteUsate.contains(port = r.nextInt(6000, 7000)));
                    porteUsate.add(port);
                    int finalPort = port;
                    int finalIdProdotto = idProdotto;
                    new Thread(() -> {
                        new SaleBroadcast(finalIdProdotto, finalPort).start();
                        ServerSocket server = null;
                        try {
                            server = new ServerSocket(finalPort);
                            while (true) { // while (!offerte.containsKey(finalIdProdotto)) {
                                Socket client = server.accept();
                                new VenditaHandler(client).start();
                            }
                        } catch (IOException e) {
                            Logging.print(Logging.Type.ERROR, "Couldn't init server on port" + finalPort, null, null, e);
                        } finally {
                            try {
                                server.close();
                            } catch (IOException e) {
                                Logging.print(Logging.Type.ERROR, "Coulnd't close socket gracefully", null, null, e);
                            }
                        }
                    });

                } catch (InterruptedException e) {
                    Logging.print(Logging.Type.ERROR, "Thread couldn't sleep", null, null, e);
                }
            }
        }).start();
    }

    class VenditaHandler extends Thread {

        private static final Object HANDLER_CLASS = Server.class;

        Socket client;
        static AtomicInteger ID_ORDINE;

        public VenditaHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                Richiesta richiesta = (Richiesta) in.readObject();

                if (prodotti.containsKey(richiesta.id)) {
                    Prodotto prodotto = prodotti.get(richiesta.id);
                    boolean inOfferta = prodotto.vendita(richiesta.pezzi);
                    int ordine = ID_ORDINE.addAndGet(1);
                    double prezzo = (inOfferta ? offerte.get(prodotto.id).prezzo : prodotto.prezzo) * richiesta.pezzi;

                    String response = String.format("Vendita effettuata %s: %d, %f\n",
                            (inOfferta) ? "con offerta" : "",
                            ordine, prezzo);
                    out.printf(response);

                    Logging.print(Logging.Type.INFO,
                            String.format("Sale completed - Order: %d, Product: %d, Amount: %.2f, Discounted: %b",
                                    ordine, richiesta.id, prezzo, inOfferta),
                            "VenditaHandler.run", HANDLER_CLASS, null);

                    ordini.put(ordine, new Ordine(ordine, LocalDate.now(), prezzo));
                }
            } catch (IOException | ClassNotFoundException e) {
                Logging.print(Logging.Type.ERROR,
                        "Failed to process sale request",
                        "VenditaHandler.run", HANDLER_CLASS, e);
            }
        }
    }

    class SaleBroadcast extends Thread {

        int idProdotto;
        int porta;

        SaleBroadcast(int idProdotto, int porta) {
            this.idProdotto = idProdotto;
            this.porta = porta;
        }

        @Override
        public void run() {
            while (offerte.containsKey(idProdotto) && offerte.get(idProdotto).pezziRimanenti > 0) {
                try {
                    Offerta offerta = offerte.get(idProdotto);
                    String msg = String.format("%d - %d - %f - %d - %d", porta, idProdotto, offerta.prezzo, offerta.sconto, offerta.pezziRimanenti);
                    send(msg);
                    TimeUnit.MINUTES.sleep(30);
                } catch (InterruptedException e) {
                    Logging.print(Logging.Type.ERROR, "Thread couldn't sleep", null, null, e);
                }
            }
        }

        void send(String msg) {
            try {
                byte[] buf = new byte[1024];
                buf = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, BROADCAST_PORT);
                Logging.print(Logging.Type.INFO, "Broadcasting offer", null, null, null);
                ms.send(packet);
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't broadcast message", null, null, e);
            }
        }
    }

    void gestioneResi() {
        new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(RESI_PORT);
                while (true) {
                    Socket client = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String request = in.readLine();
                    int id;
                    try {
                        id = Integer.parseInt(request.trim());
                    } catch (Exception e) {
                        continue;
                    }

                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    if (ordini.containsKey(id)) {
                        Double rimborso = 0.0;
                        Ordine ordine = ordini.get(id);
                        if (ordine.data.plusDays(7).isBefore(LocalDate.now())) {
                            ordini.remove(id);
                            rimborso = ordine.prezzo;
                        }
                        out.println(rimborso.toString());
                    }
                }
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't communicate with client", null, null, e);
            }
        }).start();
    }

    public static void main(String[] args) {
        Logging.print(Logging.Type.INFO,
                "Starting server application",
                "main", CLASS_NAME, null);

        Server server = new Server();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            double prezzo = r.nextDouble(10, 200);
            server.aggiungiProdotto(prezzo);
        }

        server.creaOfferte();
        server.gestioneResi();

        Logging.print(Logging.Type.INFO,
                "DB initialization completed",
                "main", CLASS_NAME, null);
    }
}
