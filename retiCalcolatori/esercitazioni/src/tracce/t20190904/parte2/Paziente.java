package tracce.t20190904.parte2;

import utils.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Paziente {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(Server.HOSTNAME, Server.PRENOTAZIONE_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("cod21497");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Couldn't init communication with server correctly", "utente", null, e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't end connection with server gracefully", "utente", null, e);
            }
        }
    }
}
