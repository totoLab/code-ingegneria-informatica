package tracce.t20190904.parte2;

import utils.Logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Direzione {

    public static void main(String[] args) {
        List<Statistica> lista = new ArrayList<Statistica>();
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(Server.STATISTICHE_PORT);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                server.receive(packet);
                ByteArrayInputStream byteIn = new ByteArrayInputStream(packet.getData());
                ObjectInputStream in = new ObjectInputStream(byteIn);
                Statistica statistica = (Statistica) in.readObject();
                lista.add(statistica);
            }
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Couldn't init notifications' receiver correctly", "notifica", null, e);
        } catch (ClassNotFoundException e) {
            Logging.print(Logging.Type.ERROR, "Received unknown object", "notifica", null, e);
        } finally {
            server.close();
        }
    }
}
