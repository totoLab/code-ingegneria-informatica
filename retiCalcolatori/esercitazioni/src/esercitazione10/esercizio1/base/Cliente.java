package esercitazione10.esercizio1.base;

import java.io.*;
import java.net.*;

import static utils.Logging.*;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket server = new Socket(InetAddress.getLocalHost(), 2345);
            Richiesta richiesta = new Richiesta(13, 2);
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(richiesta);
            oos.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println(in.readLine());

        } catch (IOException e) {
            print(Type.ERROR, "Error in connection", null, null, e);
        }
    }
}
