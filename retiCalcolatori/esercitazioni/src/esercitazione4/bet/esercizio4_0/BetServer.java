package esercitazione4.bet.esercizio4_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BetServer {

    protected HashMap<InetAddress, Scommessa> scommesse = new HashMap<>();
    static final int SERVER_TCP_PORT = 8001;
    static final String multicastGroup = "230.0.0.1";
    static final int SERVER_MULTICAST_PORT = 8002;

    private ServerSocket ss;
    protected boolean done = false;
    protected int ACCEPTING_LIMIT;
    protected int MULTICAST_DELAY;

    static void printInfo(String message) {
        System.out.println("INFO: " + message);
    }
    static void printError(String message, Exception e) {
        System.err.println(message + "\n JVM: " + e);
    }

    BetServer() {
        this(10, 10);
    }

    BetServer(int acceptingLimit, int multicastDelay) {
        this.ACCEPTING_LIMIT = acceptingLimit;
        this.MULTICAST_DELAY = multicastDelay;
    }

    void startServer() {
        new ServerManager().start();
    }

    class BetReceiver extends Thread {

        @Override
        public void run() {
            try {
                ss = new ServerSocket(SERVER_TCP_PORT);
                ss.setSoTimeout(ACCEPTING_LIMIT);
                printInfo("Server started on port " + SERVER_TCP_PORT);
                while (!done) {
                    Socket client = ss.accept();
                    printInfo("Accepted client " + client.getInetAddress() );
                    PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                    pw.println("OK: Format of bet is:horse - bet. Horse numbers are between 1 and 12.");
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String candidateBet = in.readLine();
                    printInfo("Got a new bet: " + candidateBet);
                    if (candidateBet != null) {
                        candidateBet = candidateBet.trim();
                        String[] bet = candidateBet.split("-");
                        Integer cavallo = Integer.parseInt(bet[0].trim());
                        Double puntata = Double.parseDouble(bet[1].trim());
                        Scommessa scommessa = new Scommessa(cavallo, puntata);
                        scommesse.put(client.getInetAddress(), scommessa); // TODO: warn user if bet is invalid
                    }
                    client.close();
                }
                printInfo("Receiver shutting down");
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                printError("Server's crazy", e);
            } catch (IllegalArgumentException e) {
                printError("Scommessa non valida", e);
            }

        }
    }

    private List<InetAddress> getListaVincitori() {
        Random randomGenerator = new Random();
        int cavalloVincente = randomGenerator.nextInt(1, 12);
        List<InetAddress> vincitori = new LinkedList<>();
        for (InetAddress ip : scommesse.keySet()) {
            Scommessa s = scommesse.get(ip);
            if (s.getCavallo() == cavalloVincente) {
                vincitori.add(ip);
            }
        }
        return vincitori;
    }

    private<T> String formatListAsResponse(String header, List<T> parts) {
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        sb.append(":\n");
        for (T part : parts) {
            sb.append("- ");
            sb.append(part);
            sb.append("\n");
        }
        return sb.toString();
    }

    class ResultSender extends Thread {

        @Override
        public void run() {
            super.run();
            MulticastSocket ms = null;
            try {
                ms = new MulticastSocket();
                List<InetAddress> vincitori = getListaVincitori();
                String dString = vincitori.isEmpty() ? "Nessun vincitore" : formatListAsResponse("I vincitori sono", vincitori);
                InetAddress group = InetAddress.getByName(multicastGroup);
                while (true) {
                    byte[] buf;
                    buf = dString.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, SERVER_MULTICAST_PORT);
                    ms.send(packet);
                    printInfo("Broadcasting: " + dString);
                    TimeUnit.SECONDS.sleep(MULTICAST_DELAY);
                }
            } catch (IOException e) {
                printError("Multicast failed", e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (ms != null) ms.close();
            }
        }
    }

    class ServerManager extends Thread {

        @Override
        public void run() {
            BetReceiver br = new BetReceiver();
            br.start();

            Calendar limite = Calendar.getInstance();
            limite.add(Calendar.SECOND, ACCEPTING_LIMIT);
            Calendar today = Calendar.getInstance();

            while (limite.getTime().after(today.getTime())) {
                today = Calendar.getInstance();
                try {
                    TimeUnit.SECONDS.sleep(ACCEPTING_LIMIT * 2L / 3);
                } catch (InterruptedException e) {
                    printError("Thread can't dream", e);
                }
            }
            done = true;
            try {
                br.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ResultSender rs = new ResultSender();
            rs.start();
            while (true) {
                try {
                    Socket clientToDeny = ss.accept();
                    PrintWriter pw = new PrintWriter(clientToDeny.getOutputStream(), true);
                    pw.println("CLOSED: Can't accept any more bets.");
                    clientToDeny.close();
                } catch (SocketTimeoutException e) {
                } catch (IOException e) { printError("Server error", e);}
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BetServer bs = null;
        while (bs == null || bs.done) {
            bs = new BetServer(20, 2);
            bs.startServer();
            // TimeUnit.SECONDS.sleep(40);
        }
    }

}
