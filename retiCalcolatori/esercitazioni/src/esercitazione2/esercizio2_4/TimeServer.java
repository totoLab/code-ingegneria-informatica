package esercitazione2.esercizio2_4;

import java.io.*;
import java.net.*;
import java.util.*;
public class TimeServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(3575);
            int n = 1;
            while (n <= 10) {
                byte[] buf = new byte[512];
                // riceve la richiesta
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                // produce la risposta
                String request = new String(buf).trim();
                System.out.println("Request: " + request);
                Calendar myCalendar = Calendar.getInstance(TimeZone.getTimeZone(request));
                int hour = myCalendar.get(Calendar.HOUR);
                int minutes = myCalendar.get(Calendar.MINUTE);
                String timezone = myCalendar.getTimeZone().getDisplayName();
                String dString = "Time in "+ timezone + "> "+ (hour >= 10 ? hour : "0" + hour) + ":" + (minutes >= 10 ? minutes : "0" + minutes);
                System.out.println("Response: " + dString);
                buf = dString.getBytes();
                // invia la risposta al client
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
                n++;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            socket.close();
        }
    }
}