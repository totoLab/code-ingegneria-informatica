package tracce.t20240314;

import utils.Logging;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import static utils.Logging.*;

public class Sensore {

    static int ID_HISTORY = 0;

    final private int id;
    private int temperatura;
    private int umidita;

    public Sensore(int id) {
        this.id = id;
        temperatura = -100;
        umidita = -1;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public void setUmidita(int umidita) {
        this.umidita = umidita;
    }

    private void inviaRichiesta() {
        new Thread(() -> {
            Socket server = null;
            try {
                server = new Socket("agricoltura.dimes.unical.it", 3000);
                StatoSensore stato = new StatoSensore(id, temperatura, umidita);
                ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                oos.writeObject(stato);
                BufferedReader bf = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String line = bf.readLine();
                System.out.println("(server) " + line);
            } catch (IOException e) {
                print(Logging.Type.ERROR, "Couldn't communicate with sensor", null, this, e);
            }
        }).start();
    }

    private void servizioDiNotifica() {
        new Thread(() -> {
            Socket server = null;
            try {
                // registrazione
                server = new Socket("agricoltura.dimes.unical.it", 4000);
                BufferedReader bf = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter pw = new PrintWriter(server.getOutputStream(), true);

                pw.println(id);
                String line = bf.readLine();
                System.out.println("(server) " + line);

                // listening
                while (true) {
                    DatagramSocket ds = null;
                    try {
                        ds = new DatagramSocket(4000);
                        byte[] buf = new byte[256];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        ds.receive(packet);
                        String msg = new String(packet.getData());
                        System.out.println("(notification) " + msg);
                    } catch (IOException e) {
                        print(Type.ERROR, "Couldn't communicate with sensor", null, this, e);
                    }
                }

            } catch (IOException e) {
                print(Logging.Type.ERROR, "Couldn't communicate with sensor", null, this, e);
            }
        }).start();
    }

    public static void main(String[] args) {
        Sensore sensore = new Sensore(ID_HISTORY++);
        sensore.inviaRichiesta();
        sensore.servizioDiNotifica();
    }
}
