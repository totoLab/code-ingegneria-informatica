package esercitazione9.gareappalto.base;

import utils.Logging;
import esercitazione9.gareappalto.registrogare.Offerta;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static utils.Logging.*;

public class Partecipante {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            int finalI = i;
            new Thread(() -> {
                print(Type.INFO, "Started Thread #" + finalI, null, null, null);
                try {
                    Random rand = new Random();
                    MulticastSocket multicastSocket = new MulticastSocket(3000);
                    InetAddress group = InetAddress.getByName("230.0.0.1");
                    multicastSocket.joinGroup(group);
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    boolean ok = false;
                    Integer id = -1;
                    Integer importoMassimo = -1;
                    while(!ok) {
                        multicastSocket.receive(packet);
                        String content = new String(packet.getData()).trim();
                        print(Logging.Type.INFO, "Received packet content: " + content, null, Thread.currentThread(), null);
                        String[] parts = content.split("importoMassimo=");
                        if (parts.length == 2) {
                            String idToParse = parts[0];
                            idToParse = idToParse.split("\\)\\{")[0];
                            idToParse = idToParse.replace("Richiesta(", "");
                            String importoToParse = parts[1];
                            importoToParse = importoToParse.replace("}", "");

                            try {
                                id = Integer.parseInt(idToParse);
                                importoMassimo = Integer.parseInt(importoToParse);
                                ok = true;
                            } catch (NumberFormatException e) {
                                print(Type.ERROR,"Couldn't parse something", null, null, e);
                            }
                        } else {
                            print(Type.ERROR, "AAAAAAAAAAAAAA", null, null, null);
                        }
                    }

                    multicastSocket.leaveGroup(group);

                    // ---------- //

                    Socket server = new Socket(InetAddress.getLocalHost(), 4000);
                    ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());

                    int importo = rand.nextInt(importoMassimo / 10, importoMassimo);
                    Offerta offerta = new Offerta(id, importo, finalI);

                    print(Logging.Type.INFO,"Sending offer: " + offerta, null, Thread.currentThread(), null);
                    oos.writeObject(offerta);
                    oos.flush();

                    server.close();
                    TimeUnit.SECONDS.sleep(5);

                    // ----- //

                    multicastSocket.joinGroup(group);
                    String response = "Richiesta";
                    while (response.startsWith("Richiesta")) {
                        buf = new byte[1024];
                        packet = new DatagramPacket(buf, buf.length);
                        multicastSocket.receive(packet);
                        response = new String(packet.getData()).trim();
                    }
                    print(Logging.Type.INFO, response, null, Thread.currentThread(), null);
                    multicastSocket.leaveGroup(group);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
