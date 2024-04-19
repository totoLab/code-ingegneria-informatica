package esercitazione4.communicator.slides;

import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
    static int multicastPort; // porta usata per i msg in multicast
    static int socketPort; // porta su cui il Communicator rimane in ascolto per ricevere msg TCP

    static void sendMcastDatagram() {
        try {
            while (true) {
                // Invia in multicast un datagram contenente la TCP port (socketPort) usata dall’utente
                byte[] buf = new byte[50];
                String strBuf = "";
                strBuf += socketPort;
                buf = strBuf.getBytes();
                InetAddress mcastAddress = InetAddress.getByName("230.0.0.1");
                MulticastSocket mSocket = new MulticastSocket();
                DatagramPacket dp = new DatagramPacket(buf, buf.length, mcastAddress, 2000);
                System.out.println("\nCO>Invio datagramma multicast");
                mSocket.send(dp);
                Thread.sleep(20000);
            }
        } catch (Exception ex) {
            System.out.println("CO>" + ex);
        }
    }// sendMcastDatagram

    public static void main(String args[]) {
        try {
            multicastPort = 2000; // per ricevere i datagrammi multicast
            // leggo socketPort da tastiera
            Scanner sc = new Scanner(System.in);
            System.out.print("Porta TCP locale: ");
            socketPort = Integer.parseInt(sc.next()); // porta TCP scelta dall’utente
            MulticastListener ml = new MulticastListener(multicastPort, socketPort);
            SocketListener sl = new SocketListener(socketPort);
            ml.start();
            sl.start();
            sendMcastDatagram();
        } catch (Exception ex) {
            System.out.println("CO>" + ex);
        }
    }// main
}// Communicator class

class MulticastListener extends Thread {
    int mcastPort; // usata per i msg in multicast
    InetAddress remAddress;
    int tcpPort; // è la porta su cui il Communicator è in ascolto per ricevere msg TCP
    // (questo valore verrà inserito nel msg di risposta)

    public MulticastListener(int port1, int port2) {
        this.mcastPort = port1;
        this.tcpPort = port2;
        System.out.println("ML>Porta multicast locale = " + mcastPort);
        System.out.println("ML>Porta TCP locale = " + tcpPort);
    }// costruttore

    public void run() {
        try {
            // Si iscrive al gruppo su cui viene inviato il msg in multicast
            MulticastSocket mSocket = new MulticastSocket(mcastPort);
            InetAddress group = InetAddress.getByName("230.0.0.1");
            mSocket.joinGroup(group);

            while (true) {
                // Riceve datagramma multicast ed estrae da esso l’indirizzo del mittente
                // (remAddress) e la porta del modulo server del mittente (remTCPPort),
                // cioè la porta su cui il mittente è in ascolto per ricevere msg TCP
                byte[] buf = new byte[50];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                mSocket.receive(packet);
                remAddress = packet.getAddress();
                String received = new String(packet.getData());
                int i = 0;
                while (Character.isDigit(received.charAt(i)))
                    i++;
                int remTCPPort = Integer.parseInt(received.substring(0, i));
                System.out.println("\nML>Ricevuto mcast datagram da " + remAddress.getHostAddress() + ":" +
                        packet.getPort());
                System.out.println("ML>Contenuto del datagram: " + remTCPPort);

                // Invio messaggio via TCP solo se il datagramma multicast
                // era stato inviato da un altro Communicator (non da sé stesso);
                // il messaggio di risposta contiene la porta TCP del Communicator che risponde (tcpPort),
                // cioè, la porta su cui esso riceve messaggi TCP
                if (!(remAddress.equals(InetAddress.getLocalHost())) || (remTCPPort != tcpPort)) {
                    System.out.println("ML>Invio risposta a " + remAddress.getHostAddress() + ":" + remTCPPort);
                    Socket tcpSocket = new Socket(remAddress.getHostAddress(), remTCPPort);
                    PrintWriter out = new PrintWriter(tcpSocket.getOutputStream(), true);
                    out.println(tcpPort);
                }// if
            }// while
        } catch (Exception ex) {
            System.out.println("ML>" + ex);
        }
    }// run
}// MulticastListener class

class SocketListener extends Thread {
    int tcpPort; // porta su cui il Communicator.SocketListener rimane
    // in ascolto per ricevere msg TCP
    InetAddress remAddress;
    int remTCPPort; // porta su cui il Communicator che ha risposto
    // è in ascolto per ricevere msg TCP

    public SocketListener(int port) {
        this.tcpPort = port;
        System.out.println("SL>Porta TCP locale = " + port);
    }// costruttore

    public void run() {
        try {
            ServerSocket sListener = new ServerSocket(tcpPort);
            while (true) {
                // Ricevo messaggio di risposta via TCP (è la risposta di un altro communicator al msg
                // che è stato inviato in multicast)
                Socket sock = sListener.accept();
                InetAddress remAddress = sock.getInetAddress();
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String line = in.readLine();
                // Il msg (risposta) contiene la porta TCP del communicator che ha risposto
                // e che viene usata per ricevere (a sua volta) msg TCP (remTCPPort)
                remTCPPort = Integer.parseInt(line);
                System.out.println("\nSL>Ricevuta risposta da " + remAddress.getHostAddress() + ":" +
                        sock.getPort());
                System.out.println("SL>Stringa socket ricevuta: " + remTCPPort);
                sock.close();
            }// while
        } catch (Exception ex) {
            System.out.println("SL>" + ex);
        }
    }// run
}// SocketListener class

