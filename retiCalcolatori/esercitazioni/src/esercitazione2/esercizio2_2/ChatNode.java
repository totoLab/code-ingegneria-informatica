package esercitazione2.esercizio2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ChatNode {

    private final List<Socket> clients = Collections.synchronizedList(new ArrayList<Socket>()); // TODO: removing clients only if get EXIT command
    private final int port;
    private ServerSocket server;

    public ChatNode(int port) {
        if (1023 < port && port <= 65536) this.port = port;
        else throw new RuntimeException("Invalid port");
        try {
            server = new ServerSocket(this.port);
        } catch (IOException e) {
            printError("Couldn't create server on port " + port, e);
        }
        loop();
    }

    private void printError(String message, Exception e) {
        System.err.println(message + "\n JVM: " + e);
    }

    private void loop() {

        new Thread(() -> {
            System.out.println("INFO: Now accepting connections...");
            boolean connected = false;
            try {
                while (true) {
                    Socket client = server.accept();
                    System.out.println("INFO: Connected to " + client.getInetAddress());

                    boolean addClient = true;
                    for (Socket s : clients) {
                        if (client.getInetAddress().equals(s.getInetAddress())) addClient = false;
                    }
                    if (addClient) clients.add(client);

                    if (!connected) {
                        connected = true;
                        switchClient();
                    }
                }
            } catch (IOException e) {
                printError("Couldn't accept client", e);
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    printError("Couldn't gracefully shut down server.", e);
                }
            }
        }).start();

        new Thread(() -> {
            ArrayList<String> peers = new ArrayList<>();
            peers.add("172.20.10.5");
            peers.add("172.20.10.4");
            peers.add("172.20.10.2");
            try {
                Iterator<String> it = peers.iterator();
                while (!peers.isEmpty()) {
                    TimeUnit.SECONDS.sleep(3);
                    String host = it.next();
                    Socket anotherServer = new Socket(host, 2222);
                    for (Socket client : clients) {
                        if (client.getInetAddress().equals(anotherServer.getInetAddress())) break;
                    }
                    it.remove();
                    clients.add(anotherServer);
                    if (!it.hasNext()) it = peers.iterator();
                }
            } catch (InterruptedException e) {
                printError("Couldn't sleep", e);
            } catch (IOException e) {
                printError("Couldn't accept client", e);
            }
        }).start();
    }

    private void switchClient() {
        new Thread(() -> {
            Socket currentSocket = null;
            long last = System.currentTimeMillis();
            long interval = 1000 * 10; // 10 seconds
            int index = 0;
            while (!clients.isEmpty()) {
                long now = System.currentTimeMillis();
                if (now - last > interval) {
                    if (!clients.isEmpty()) {
                        currentSocket = clients.get(index % clients.size());
                        System.out.println("INFO: switching to client " + currentSocket.getInetAddress());
                        handleClient(currentSocket);
                        index++;
                    }
                }
                last = now;
            }
        }).start();
    }

    private void handleClient(Socket socket) {

        // in
        new Thread(() -> {
            System.out.println("INFO: Spawning input Thread for socket " + socket.getInetAddress());
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    System.out.println(socket.getLocalAddress() + ": " + in.readLine());
                }
            } catch (IOException e) {
                printError("Couldn't receive stream correctly", e);
            } finally {
                if (in != null) {
                    try { in.close(); }
                    catch (IOException e) {
                        printError("Couldn't close BufferedReader for socket " + socket.getInetAddress(), e);
                    }
                }
            }
        }).start();

        // out
        new Thread(() -> {
            System.out.println("INFO: Spawning output Thread for socket " + socket);
            PrintWriter out = null;
            Scanner user = null;
            try {
                out = new PrintWriter(socket.getOutputStream());
                user = new Scanner(System.in);
                while (true) {
                    String message = user.nextLine();
                    out.println(message);
                }
            } catch (IOException e) {
                printError("Couldn't send stream correctly", e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new ChatNode(2222);
    }

}

