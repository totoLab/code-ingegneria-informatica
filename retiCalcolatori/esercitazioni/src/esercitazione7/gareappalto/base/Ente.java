package esercitazione7.gareappalto.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Ente {

    public static void main(String[] args) {
        try {
            Richiesta richiesta = new Richiesta("Ponte sullo Stretto", 5000);
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
    }
}
