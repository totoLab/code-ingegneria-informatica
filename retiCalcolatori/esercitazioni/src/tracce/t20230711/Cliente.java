package tracce.t20230711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        String richiesta = "123#IT#true";
        try {
            Socket server = new Socket(InetAddress.getLocalHost(),4000);
            PrintWriter pw = new PrintWriter(server.getOutputStream());
            pw.println(richiesta);
            BufferedReader bf = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String response = bf.readLine();
            System.out.println(response);

        } catch (IOException e) {

        }
    }
}
