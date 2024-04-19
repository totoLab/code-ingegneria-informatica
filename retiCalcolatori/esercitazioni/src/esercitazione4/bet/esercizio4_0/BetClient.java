package esercitazione4.bet.esercizio4_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class BetClient {

    private InetAddress SERVER_IP;
    static final int SERVER_TCP_PORT = 8001;
    static final String multicastGroup = "230.0.0.1";
    static final int SERVER_MULTICAST_PORT = 8002;


    BetClient() {
        try {
            SERVER_IP = InetAddress.getLocalHost();
        } catch (IOException e) { printError("Couldn't get IP", e); }

        entryPoint();
    }

    static void printInfo(String message) {
        System.out.println("INFO: " + message);
    }
    static void printError(String message, Exception e) {
        System.err.println(message + "\n JVM: " + e);
    }

    void entryPoint() {
        BufferedReader in = null;
        PrintWriter pw = null;
        try {
            printInfo("Trying to connect to server " + SERVER_IP);
            Socket server = new Socket(SERVER_IP, SERVER_TCP_PORT);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String line = "";
            line = in.readLine();
            System.out.println(line);
            if (line.startsWith("OK")) {
                Scanner user = new Scanner(System.in);
                String bet = user.nextLine();
                pw = new PrintWriter(server.getOutputStream(), true);
                pw.println(bet);
                printInfo("Bet sent!");
            } else if (line.startsWith("CLOSED")) {
                // falltrough
            } else {
                printError("Unexpected message", new Exception());
            }
        } catch (IOException e) {
            BetServer.printError("Something failed", e);
        } finally {
            try {
                if (in != null) in.close();
                if (pw != null) pw.close();
            } catch (IOException e) {
                printError("Couldn't shut down pipe gracefully", e);
            }
        }

        printInfo("Now wait for the bet's results");
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket(SERVER_MULTICAST_PORT);
            InetAddress group = InetAddress.getByName(multicastGroup);
            ms.joinGroup(group);
            printInfo("Joined group" + group);
            byte[] buf = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            ms.receive(packet);
            buf = packet.getData();
            String dString = new String(buf).trim();
            System.out.println(dString);
            ms.leaveGroup(group);
        } catch (IOException e) {
            BetServer.printError("Something multicasty happened", e);
        }

    }


    public static void main(String[] args) { new BetClient(); }
}
