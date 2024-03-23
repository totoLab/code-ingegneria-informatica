package esercitazione2.esercizio2_2;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChatNode {

    private final List<Socket> clients = new ArrayList<>(); // TODO: removing clients only if get EXIT command
    private final Semaphore mutexClientsList = new Semaphore(1);
    private final int port;
    public static final int DEFAULT_SERVER_PORT = 2222;
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
    } // initialize server + execute loop

    synchronized static void printInfo(String message) {
        System.out.println("INFO: " + message);
    }

    synchronized static void printError(String message, Exception e) {
        System.err.println(message + "\nJVM: " + e);
    }

    private void handleClient(Socket socket) {
        Semaphore mutexSocket = new Semaphore(1);
        // in
        InputHandler ih = new InputHandler(socket, mutexSocket);
        ih.start();

        // out
        OutputHandler oh = new OutputHandler(socket, mutexSocket);
        oh.start();
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

    class ClientAcceptor extends Thread {
        public void run() {
            printInfo("Now accepting connections...");
            try {
                while (true) {
                    Socket client = server.accept();
                    printInfo("Connected to " + client.getInetAddress());
                    if (!checkIfConnected(client.getInetAddress().toString())) {
                        mutexClientsList.acquire();
                        clients.add(client);
                        mutexClientsList.release();
                        switchClient();
                    }
                }
            } catch (IOException | InterruptedException e) {
                printError("Couldn't accept client", e);
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    printError("Couldn't gracefully shut down server.", e);
                }
            }
        } // run
    } // class

    class PeerConnector extends Thread {
        public void run() {
            List<String> peers = getIpsFromFile("ip.txt");
            if (peers.isEmpty()) return; //! disabling connecting to peers if list is empty
            try {
                boolean more=true;
                while(more){
                    Iterator<String> it = peers.iterator();
                    while (it.hasNext()) {
                        //TimeUnit.SECONDS.sleep(3); Perchè?
                        String host = it.next();
                        if (checkIfConnected(host)) {
                            it.remove();
                        } else {
                            try{
                                Socket anotherNode = new Socket(host, DEFAULT_SERVER_PORT);
                                mutexClientsList.acquire();
                                clients.add(anotherNode);
                                mutexClientsList.release();
                            } catch (IOException e) {
                                printError("Couldn't connect to host: "+host, e);
                            }
                        }
                    }
                    if (peers.isEmpty()) more=false;
                }
            } catch (InterruptedException e) {
                printError("Couldn't sleep", e);
            }
        } // run
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

    private boolean checkIfConnected(String host) {
        try {
            mutexClientsList.acquire();
            for (Socket client : clients) {
                if (client.getInetAddress().toString().equals(host)) {
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
    } // class

    class InputHandler extends Thread {
        Socket socket;
        Semaphore mutexSocket;
        public InputHandler(Socket socket, Semaphore mutexSocket) {
            this.socket=socket;
            this.mutexSocket=mutexSocket;
        }
        public void run(){
            BufferedReader in = null;
            InetAddress locAddr = null;
            try {
                mutexSocket.acquire();
                locAddr = socket.getLocalAddress();
                printInfo("Spawning input Thread for socket " + locAddr);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                mutexSocket.release();
                while (true) {
                    String line = in.readLine();
                    if(line!=null)
                        System.out.println(locAddr + ": " + line);
                }
            } catch (InterruptedException e) {
                printError("Thread killed", e);
            } catch (IOException e) {
                printError("Couldn't receive stream correctly", e);
            } finally {
                if (in != null) {
                    try { in.close(); }
                    catch (IOException e) {
                        printError("Couldn't close BufferedReader for socket " + locAddr, e);
                    }
                }
            }
        }
    }

    class OutputHandler extends Thread {
        Socket socket;
        Semaphore mutexSocket;
        public OutputHandler(Socket socket, Semaphore mutexSocket) {
            this.socket=socket;
            this.mutexSocket=mutexSocket;
        }
        public void run(){
            InetAddress locAddr = null;
            PrintWriter out = null;
            Scanner user = null;
            try {
                mutexSocket.acquire();
                locAddr = socket.getLocalAddress();
                printInfo("Spawning output Thread for socket " + locAddr);
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
}