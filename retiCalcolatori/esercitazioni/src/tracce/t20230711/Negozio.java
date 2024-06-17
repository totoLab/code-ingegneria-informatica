package tracce.t20230711;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.TreeSet;

public class Negozio {

    public static void main(String[] args) {
        Offerta offerta = new Offerta("123", "IT", "234", 12.0, 3);
        TreeSet<String> offerteDaBattere = new TreeSet<>();
        Socket server = null;
        try {
            server = new Socket(InetAddress.getLocalHost(), 3000);
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(offerta);
            oos.flush();

            InetAddress group = InetAddress.getByName("230.0.0.1");
            MulticastSocket ms = new MulticastSocket(5000);
            ms.joinGroup(group);
            byte[] buf = new byte[1024];
            for (int i = 0; i < 10; i++) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ms.receive(packet);
                String data = new String(packet.getData()).trim();
                offerteDaBattere.add(data);
            }
            ms.leaveGroup(group);

            String migliore = offerteDaBattere.getFirst();
            // ...
        } catch (IOException e) {

        }
    }

}
