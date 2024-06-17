package tracce.t20220614.parte2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Utente {

    public static void main(String[] args) {
        final String ip = "230.0.0.1";
        final int PORT = 6000;
        ArrayList<String> offerteDisponibili = new ArrayList<>();
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket();
            InetAddress hostname = InetAddress.getByName(ip);

            ms.joinGroup(hostname);

            for (int i = 0; i < 10; i++) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ms.receive(packet);
                String offerta = new String(buf).trim();
                String[] parts = offerta.split("#");
                String id = parts[0];
            }

            Random rand = new Random();
            int index = rand.nextInt(0, offerteDisponibili.size() - 1);
            String offertaOk = offerteDisponibili.get(index);
            int id = Integer.parseInt(offertaOk);
            Candidatura candidatura = new Candidatura(id, "antolab.xyz/curriculum");
            Socket server = new Socket("job.unical.it", 4000);
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(candidatura);
            oos.flush();
        } catch (IOException e) {

        }
    }
}
