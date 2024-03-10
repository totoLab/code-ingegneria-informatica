package esercitazione1.overeng;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerClient {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Socket socket = null;
        int port = 8189;
        try {
            InetAddress server = InetAddress.getLocalHost();
            socket = new Socket(server, port);

            boolean done = false;
            while (!done) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );
                String response = in.readLine();
                System.out.println(response);

                String userInput = s.next();
                String message = userInput;
                if (userInput.equals("BYE")) {
                    done = true;
                } else if (userInput.equals("EXIT")) {
                    if (!socket.isClosed()) {
                        message = "BYE";
                    }
                    done = true;
                }
                sendToSocket(socket, message);

                if (done) {
                    socket.close();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void sendToSocket(Socket socket, String message) throws IOException {
        PrintWriter pw = new PrintWriter(
                socket.getOutputStream(), true
        );
        pw.println(message);
    }
}
