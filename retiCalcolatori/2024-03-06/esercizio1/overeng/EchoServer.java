package esercitazione1.overeng;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args ) {
        try {
            ServerSocket s = new ServerSocket(8189);
            while (true) {
                System.out.println("Waiting for a connection...");
                Socket incoming = s.accept(); // stop in attesa di una connessione
                System.out.println("Connected to " + incoming.getInetAddress() + ":" + incoming.getPort());
                BufferedReader in = new BufferedReader
                        (new InputStreamReader(incoming.getInputStream()));
                PrintWriter out = new PrintWriter
                        (incoming.getOutputStream(), true /* autoFlush */);
                out.println("Hello! Enter BYE to exit.");
                boolean done = false;
                while (!done) {
                    String line = in.readLine();
                    System.out.println("Received: " + line);

                    if (line == null)
                        done = true;
                    else {
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE"))
                            done = true;
                    }
                }
                incoming.close();
            }
        } catch (Exception e) { System.err.println(e); }
        finally { s.close(); }
    }
}