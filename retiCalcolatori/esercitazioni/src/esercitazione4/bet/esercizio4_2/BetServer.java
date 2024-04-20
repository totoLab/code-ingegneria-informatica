package esercitazione4.bet.esercizio4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import static esercitazione4.bet.esercizio4_2.Logging.*;

public class BetServer {

    static final int SERVER_TCP_PORT = 8001;
    private final int STARTER_DELAY_SECONDS = 1;

    private Dispatcher dispatcher;
    private Starter starter;
    private GameManager gameManager;

    BetServer(List<Race> races) {
        dispatcher = new Dispatcher();
        dispatcher.start();
        starter = new Starter();
        starter.start();
        gameManager = new GameManager(races);
        gameManager.start();
    }

    class Dispatcher extends Thread {

        @Override
        public void run() {
            ServerSocket server = null;
            try {
                server = new ServerSocket(SERVER_TCP_PORT);
                print(Type.INFO, "Server started on port " + SERVER_TCP_PORT, null, this, null);
                while (true) {
                    Socket client = server.accept();
                    print(Type.INFO, "Accepted client " + client, null, this, null);
                    starter.addClient(client);
                }
            } catch (IOException e) { print(Type.ERROR, "Couldn't accept clients", null, this, e);}
        }
    }

    class Starter extends Thread {

        LinkedList<Socket> clients = new LinkedList<>();
        private int managed = 0;

        public void addClient(Socket client) {
            clients.add(client);
        }

        @Override
        public void run() {
            print(Type.INFO, "Started managing requests", null, this, null);
            try {
                while (true) {
                    if (!clients.isEmpty()) {
                        while (managed > clients.size()) {
                            print(Type.INFO, "No clients available, sleeping for " + STARTER_DELAY_SECONDS, null, null, null);
                            Thread.sleep(STARTER_DELAY_SECONDS * 1000);
                        }
                        Socket client = clients.getLast();
                        new ClientManager(client).start();
                        managed++;
                    }
                }
            } catch(InterruptedException e){
                print(Type.ERROR, "Thread couldn't sleep", null, this, e);
            }
        }
    }

    class ClientManager extends Thread {

        Socket client;

        ClientManager(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            print(Type.INFO, "Trying to communicate with client " + client, null, null, null);
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                String races = gameManager.getRacesAsString();
                String[] raceLines = races.split("\n");
                for (String line : raceLines) {
                    out.println(line);
                }
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String response = in.readLine();
                Bet bet = new Bet(client.getInetAddress(), response);
                String responseToClient;
                if (gameManager.placeBet(bet)) {
                    responseToClient = "Bet placed correctly";
                } else {
                    responseToClient = "Bet can't be placed";
                }
                out.println(responseToClient);
                print(Type.INFO, responseToClient, null, this, null);
            } catch (IOException e) {
                print(Type.ERROR,"Can't communicate with client", null, this, e);
            } catch (IllegalArgumentException e) {
                if (out != null) out.println(Bet.usage());
            }
        }

    }

    class GameManager extends Thread {

        LinkedList<Race> races;

        GameManager(List<Race> races) {
            this.races = new LinkedList<>(races);
        }

        public boolean placeBet(Bet bet) {
            for (Race race : races) {
                if (race.getRaceId() == bet.getRace()) {
                    return race.placeBet(bet);
                }
            }
            return false;
        }

        public String getRacesAsString() {
            StringBuilder sb = new StringBuilder();
            for (Race race : races) {
                if (race.isActive()) {
                    sb.append("- ");
                    sb.append(race.toString());
                    sb.append("\n");
                }
            }
            return sb.toString();
        }

        @Override
        public void run() {
            print(Type.INFO, "Started managing races", null, this, null);
            Date now;
            while (true) {
                for (Race race : races) {
                    now = Calendar.getInstance().getTime();
                    if (
                            race.getStartBetting().before(now) &&
                                    race.getStartTime().after(now)
                    ) {
                        race.setActive(true);
                        race.start();
                    }

                }
            }
        }
    }

    private static List<Race> generateRaceList(int numberOfRaces) {
        List<Race> races = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfRaces; i++) {
            int raceId = i + 1; // Race IDs start from 1
            // Generate a random start time within the next 24 hours
            long currentTimeMillis = System.currentTimeMillis();
            long randomOffset = random.nextInt(24 * 60 * 60 * 1000); // Random offset within 24 hours in milliseconds
            Date startTime = new Date(currentTimeMillis + randomOffset);
            races.add(new Race(raceId, startTime));
        }
        return races;
    }

    public static void main(String[] args) {
        List<Race> races = generateRaceList(10);
        BetServer bs = new BetServer(races);
    }

}
