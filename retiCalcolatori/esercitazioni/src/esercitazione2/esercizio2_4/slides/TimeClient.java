package esercitazione2.esercizio2_4.slides;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class TimeClient {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        DatagramSocket socket = new DatagramSocket();
        // invia la richiesta
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 3575);
        socket.send(packet);
        // riceve la risposta
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        // visualizza la risposta
        String received = new String(packet.getData());
        System.out.println("Response: " + received);
        socket.close();
    }
}
