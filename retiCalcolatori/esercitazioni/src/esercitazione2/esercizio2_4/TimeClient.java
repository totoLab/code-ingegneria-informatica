package esercitazione2.esercizio2_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        DatagramSocket socket = new DatagramSocket();
        // invia la richiesta
        InetAddress address = InetAddress.getByName(hostname);

        final byte[] buf = new byte[512];
        // Send the packet
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 3575);
        // String timezone = "US/Alaska";
        Scanner user = new Scanner(System.in);
        System.out.println("Enter timezone: ");
        String timezone = user.nextLine();
        packet.setData(timezone.getBytes());
        socket.send(packet);
        // riceve la risposta
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        // visualizza la risposta
        String received = new String(packet.getData()).trim();
        System.out.println("Response: " + received);
        socket.close();
    }
}
