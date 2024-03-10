package esercitazione1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerClient {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        Socket s = null;
        try {
            s = new Socket(InetAddress.getLocalHost(), 8189);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String response = in.readLine();
            while (response != null) {
                System.out.println(response);

                String userMessage = user.next();
                if (userMessage.trim().equals("EXIT")) {
                    pw.println("BYE");
                    break;
                }
                pw.println(userMessage);

                response = in.readLine();
            }
            s.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
