package esercitazione2.esercizio2_1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class HttpWelcomeClient {

    public static void main(String[] args) {
        int port = 1025;
        Socket server = null;
        StringBuilder sb = new StringBuilder();
        try {
            server = new Socket(InetAddress.getLocalHost(), port);
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter
                    (server.getOutputStream(), true);

            sb.append("GET");
            sb.append(" / ");
            sb.append("\n\n");
            out.println(sb.toString());

            sb.delete(0, sb.length() - 1);
            String line = in.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = in.readLine();
            }
            System.out.println(sb.toString());
            server.close();
        } catch (Exception e) { System.out.println(e); }
    }
}
