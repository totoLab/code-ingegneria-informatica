package esercitazione2.esercizio2_2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatNode {

    private final List<Socket> clients = new ArrayList<>(); // TODO: removing clients only if get EXIT command
    private final Semaphore mutexClientsList = new Semaphore(1);
    private final int port;
    public static final int DEFAULT_SERVER_PORT = 2222;
    private ServerSocket server;

    private AtomicInteger idChatNode = new AtomicInteger(0);

    public ChatNode(int port) {
        if (1023 < port && port <= 65536) this.port = port;
        else throw new RuntimeException("Invalid port");
        try {
            server = new ServerSocket(this.port);
        } catch (IOException e) {
            printError("Couldn't create server on port " + port, e);
        }
        loop();
    } // initialize server + execute loop

    synchronized static void printInfo(String message) {
        System.out.println("INFO: " + message);
    }

    synchronized static void printError(String message, Exception e) {
        System.err.println(message + "\nJVM: " + e);
    }

    synchronized static void printMessage(String message) {
        System.out.println("MESSAGE: " + message);
    }

    public static void main(String[] args) throws IOException {
        new ChatNode(DEFAULT_SERVER_PORT);
    }

    private void loop() {
        ClientAcceptor ca = new ClientAcceptor();
        ca.start();
        PeerConnector pc = new PeerConnector();
        pc.start();
    }

    class PeerConnector extends Thread {
        public void run() {
            List<String> peers = getIpsFromFile("ip.txt");
            if (peers.isEmpty()) return; //! disabling connecting to peers if list is empty
            for (String address : peers) {
                try {
                    Socket serverNode = new Socket(address, DEFAULT_SERVER_PORT);
                    mutexClientsList.acquire();
                    clients.add(serverNode);
                    mutexClientsList.release();
                    printInfo("Connected to server chatNode: " + serverNode.getInetAddress());
                } catch (IOException e) {
                    printError("Couldn't connect to server chatNode: " + address, e);
                } catch (InterruptedException e) {
                    printError("Couldn't sleep", e);
                }
            }
        }
        public void oldRun () {
            try {
                List<String> peers = getIpsFromFile("ip.txt");
                if (peers.isEmpty()) return; //! disabling connecting to peers if list is empty
                boolean more = true;
                while (more) {
                    Iterator<String> it = peers.iterator();
                    while (it.hasNext()) {
                        //TimeUnit.SECONDS.sleep(3); //Perchè?
                        String host = it.next();
                        if (checkIfConnected(host)) {
                            it.remove();
                        } else {
                            try {
                                Socket serverNode = new Socket(host, DEFAULT_SERVER_PORT);
                                mutexClientsList.acquire();
                                clients.add(serverNode);
                                mutexClientsList.release();
                                printInfo("Connected to server chatNode: " + serverNode.getInetAddress());
                            } catch (IOException e) {
                                printError("Couldn't connect to server chatNode: " + host, e);
                            }
                        }
                    }
                    if (peers.isEmpty()) more = false;
                }
            } catch (InterruptedException e) {
                printError("Couldn't sleep", e);
            }
        }
    } // class

    private List<String> getIpsFromFile(String path) {
        List<String> ret = null;
        File f = null;
        try {
            f = new File(path);
            BufferedReader bf = new BufferedReader(new FileReader(f));
            ret = bf.lines().filter(e -> !e.startsWith("#")).toList();
        } catch (Exception e) {
            printError("Couldn't read file" + f, e);
        }
        return ret;
    }

    class ClientAcceptor extends Thread {
        public void run() {
            printInfo("Now accepting connections...");
            try {
                while(true) {
                    Socket client = server.accept();
                    printInfo("Accepted client chatNode: " + client.getInetAddress());
                    mutexClientsList.acquire();
                    clients.add(client);
                    mutexClientsList.release();
                    handleClient(client);
                }
            } catch (IOException | InterruptedException e) {
                printError("Couldn't accept a client chatNode", e);
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    printError("Couldn't gracefully shut down server.", e);
                }
            }
        } // run
    } // class

    private void handleClient(Socket client) {
        int id=idChatNode.addAndGet(1);
        printInfo("Starting to serve ChatNode " + id + ": " + client.getInetAddress());
        Semaphore mutexClient = new Semaphore(1);
        // in
        InputHandler ih = new InputHandler(client, mutexClient, id);
        ih.start();
        // out
        OutputHandler oh = new OutputHandler(client, mutexClient, id);
        oh.start();
    }

    static class InputHandler extends Thread {
        Socket client;
        Semaphore mutexClient;
        int id;
        public InputHandler(Socket client, Semaphore mutexClient, int id) {
            this.client= client;
            this.mutexClient =mutexClient;
            this.id=id;
        }
        public void run(){
            BufferedReader in = null;
            InetAddress addr = null;
            try {
                mutexClient.acquire();
                addr = client.getInetAddress();
                printInfo("Spawning input handler of chatNode " + id + ": " + addr);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                mutexClient.release();
                printMessage("First test message from chatNode "+id);
                while(true) {
                    printMessage("Another test message from chatNode "+id);
                    String line = in.readLine();
                    printMessage("Another wewe test message from chatNode "+id);
                    if(line!=null)
                        printMessage("from chatNode "+id + ": " + line);
                }
            } catch (InterruptedException e) {
                printError("Thread killed", e);
            } catch (IOException e) {
                printError("Couldn't receive stream correctly", e);
            } finally {
                if (in != null) {
                    try { in.close(); }
                    catch (IOException e) {
                        printError("Couldn't close BufferedReader for socket " + addr, e);
                    }
                }
            }
        } // run
    }

    static class OutputHandler extends Thread {
        Socket socket;
        Semaphore mutexSocket;
        int id;
        public OutputHandler(Socket socket, Semaphore mutexSocket, int id) {
            this.socket=socket;
            this.mutexSocket=mutexSocket;
            this.id=id;
        }
        public void run(){
            InetAddress addr = null;
            PrintWriter out = null;
            Scanner user = null;
            try {
                mutexSocket.acquire();
                addr = socket.getInetAddress();
                printInfo("Spawning output handler of chatNode " + id + ": " + addr);
                out = new PrintWriter(socket.getOutputStream(), true);
                mutexSocket.release();
                user = new Scanner(System.in);
                while (true) {
                    String message = user.nextLine();
                    out.println(message);
                }
            } catch (IOException e) {
                printError("Couldn't send stream correctly", e);
            } catch (InterruptedException e) {
                printError("Thread killed", e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    //Not used anymore
    private boolean checkIfConnected(String address) {
        try {
            mutexClientsList.acquire();
            for (Socket client : clients) {
                if (client.getInetAddress().toString().equals(address)) {
                    mutexClientsList.release();
                    return true;
                }
            }
        } catch (InterruptedException e) {
            printError("Couldn't read clients", e);
        }
        mutexClientsList.release();
        return false;
    }
    private void switchClient() {
        ClientSwitcher cs= new ClientSwitcher();
        cs.start();
    }
    class ClientSwitcher extends Thread {
        public void run(){
            for(Socket client : clients){
                handleClient(client);
            }
        }
        public void run2(){
            Socket currentSocket = null;
            long last = System.currentTimeMillis();
            long interval = 1000 * 10; // 10 seconds
            int index = 0;
            try {
                mutexClientsList.acquire();
                while (!clients.isEmpty()) {
                    long now = System.currentTimeMillis();
                    if (now - last > interval) {
                        if (!clients.isEmpty()) {
                            currentSocket = clients.get(index % clients.size());
                            printInfo("Switching to client " + currentSocket.getInetAddress());
                            handleClient(currentSocket);
                            index++;
                        }
                    }
                    last = now;
                }
                mutexClientsList.release();
            } catch (InterruptedException e) {
                printError("Couldn't switch client", e);
            }
        }
    }

}