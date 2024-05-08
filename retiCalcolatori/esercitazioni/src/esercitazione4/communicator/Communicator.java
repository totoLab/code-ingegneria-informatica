package esercitazione4.communicator;

import utils.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;

import static java.lang.Math.pow;
import static utils.Logging.Type;
import static utils.Logging.print;

public class Communicator {

    private final String multicastGroup = "230.0.0.1";
    private final int multicastPort = 2000;
    private int TCP_PORT;
    protected InetAddress host;

    Communicator() {
        try {
            this.host = InetAddress.getLocalHost();
            this.TCP_PORT = getRandomPort();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        entrypoint();
    }

    void entrypoint() {
        print(Type.INFO, "Created communicator", null, this, null);
        try {
            MulticastListener ml = new MulticastListener();
            SocketListener sl = new SocketListener();
            ml.start();
            sl.start();
            sendMcastDatagram();
        }
        catch (Exception e) {
            print(Type.ERROR, "Some error somewhere here help me", null, this, e);
        }
    }

    private static int getRandomPort() {
        Random random = new Random();
        int min = (int) pow(2, 10);
        int max = (int) pow(2, 16) - 1;
        return random.nextInt(max - min) + min;
    }

    void sendMcastDatagram() {
        print(Type.INFO, "Starting beacon for other communicators", null, this, null);
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket();
            InetAddress group = InetAddress.getByName(multicastGroup);
            byte[] buf;
            while (true) {
                buf = ( host +
                        ":" + TCP_PORT
                ).getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, multicastPort);
                socket.send(packet);
            }
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't communicate via multicast correctly", null, this, e);
        } finally {
            if (socket != null) socket.close();
        }
    }

    class SocketListener extends Thread {

        @Override
        public void run() {
            ServerSocket server = null;
            Socket client = null;
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                server = new ServerSocket(TCP_PORT);
                print(Type.INFO, "Started TCP server " + host + ":" + TCP_PORT, null, this, null);
                client = server.accept();
                print(Type.INFO, "Accepted client " + client.getInetAddress(), null, this, null);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream());
                while (true) { // by convenction, server sends first message
                    out.println("Coonection started, say hi!");
                    String message = in.readLine();
                    System.out.println(message);
                }
            } catch (IOException e) {
                print(Logging.Type.ERROR, "TCP connection failed", null, this, e);
            } finally {
                try {
                    if (client != null) client.close();
                    if (server != null) server.close();
                    if (in != null) in.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    print(Type.ERROR, "Couldn't gracefully shutdown socket", null, this, e);
                }
            }
        }
    }

    class MulticastListener extends Thread {

        static class NodeId {
            InetAddress host;
            int port;

            NodeId(String response) throws UnknownHostException {
                String[] parts = response.split(":");
                if (parts.length == 2) {
                    String name = parts[0].split("/")[0];

                    this.host = InetAddress.getByName(name);
                    this.port = Integer.parseInt(parts[1]);
                }
            }

            NodeId(InetAddress host, int port ) {
                this.host = host;
                this.port = port;
            }

            public InetAddress getHost() {
                return host;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (obj == this) return true;
                if (!(obj instanceof NodeId)) return false;

                NodeId o = (NodeId) obj;
                return o.host.equals(this.host) && o.port == this.port;
            }
        }

        @Override
        public void run() {
            MulticastSocket socket = null;
            try {
                socket = new MulticastSocket(multicastPort);
                InetAddress group = InetAddress.getByName(multicastGroup);
                socket.joinGroup(group);
                byte[] buf = new byte[256];
                boolean done = false;
                while (!done) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    NodeId node = null;
                    try {
                        node = new NodeId(new String(buf).trim());
                        // TODO handle node
                        if (!node.equals(new NodeId(host, TCP_PORT))) {
                            print(Type.INFO, "Trying to connect to " + node, null, this, null);
                        }
                        done = true;
                    } catch (UnknownHostException e) {
                        print(Type.ERROR, "Couldn't find host", null, this, e);
                    }
                }
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't communicate via multicast correctly", null, this, e);
            } finally {
                if (socket != null) socket.close();
            }
        }
    }

    public static void main(String[] args) {
        new Communicator();
    }
}
