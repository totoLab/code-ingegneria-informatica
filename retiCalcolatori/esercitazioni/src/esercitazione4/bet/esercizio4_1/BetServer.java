package esercitazione4.bet.esercizio4_1;

import esercitazione4.bet.esercizio4_0.Scommessa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BetServer {

    protected HashMap<InetAddress, Scommessa> scommesse = new HashMap<>();
    static final int SERVER_TCP_PORT = 8001;
    static final String multicastGroup = "230.0.0.1";
    static final int SERVER_MULTICAST_PORT = 8002;

    private ServerSocket ss;
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

    class BetManager extends Thread {

        boolean accepting = true;

        @Override
        public void run() {
            try {
                ss = new ServerSocket(SERVER_TCP_PORT);
                printInfo("Server started on port " + SERVER_TCP_PORT);
                while (true) {
                    Socket client = ss.accept();
                    printInfo("Accepted client " + client.getInetAddress() );
                    PrintWriter pw = new PrintWriter(client.getOutputStream(), true);

                    if (accepting) {
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
                    } else {
                        pw.println("CLOSED: Can't accept any more bets.");
                    }
                    client.close();
                }
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                printError("Server's crazy", e);
            } catch (IllegalArgumentException e) {
                printError("Scommessa non valida", e);
            }
            printInfo("Receiver shutting down");
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

    void startServer() {
        BetManager bm = new BetManager();
        bm.start();

        try {
            TimeUnit.SECONDS.sleep(ACCEPTING_LIMIT);
        } catch (InterruptedException e) { printError("Thread couldn't sleep", e);}
        bm.accepting = false;

        new ResultSender().start();
    }

    public static void main(String[] args) {
        BetServer bs = new BetServer(15 ,2);
        bs.startServer();
    }
}
