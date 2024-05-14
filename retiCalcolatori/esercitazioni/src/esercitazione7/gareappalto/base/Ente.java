package esercitazione7.gareappalto.base;

import esercitazione7.gareappalto.slides.Richiesta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Ente {

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            int importo = rand.nextInt(4000, 5000);
            Richiesta richiesta = new Richiesta(i, "Ponte #" + (i + 1), importo);
            new Thread(() -> {
                try {
                    Socket server = new Socket(InetAddress.getLocalHost(), 2000);
                    ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                    System.out.println("Sent request " + richiesta);
                    oos.writeObject(richiesta);
                    oos.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    String response = br.readLine();
                    System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
