package tracce.t20230619.parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Azienda {

    public static void main(String[] args) {
        ArrayList<String> domande = (ArrayList<String>) Arrays.asList(new String[]{"d1", "d2", "d3"});
        Sondaggio sondaggio = new Sondaggio( "Sample", domande);
        try {
            Socket server = new Socket(InetAddress.getLocalHost(), 3000);
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(sondaggio);
            oos.flush();
            BufferedReader bf = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String response = bf.readLine();
            System.out.println(response);
        } catch (IOException e) {

        }
    }
}
