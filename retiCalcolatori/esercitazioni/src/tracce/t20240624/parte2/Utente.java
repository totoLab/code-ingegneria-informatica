package tracce.t20240624.parte2;

import utils.Logging;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Random;

public class Utente {

    String username;

    Utente(String username) {
        this.username = username;
    }

    void richiesta() {
        Random rand = new Random();
        int index = rand.nextInt(Nazionale.values().length);
        Socket server = null;
        try {
            server = new Socket(InetAddress.getLocalHost(), Server.USERS_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String welcomeMessage = in.readLine();
            System.out.println(welcomeMessage);
            if (welcomeMessage.startsWith("500")) {
                return;
            }

            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            LocalTime time = LocalTime.of(18, 0);
            String msg = String.format("%s-%s-%s", username, Nazionale.values()[index], time);
            System.out.printf("Sending request %s\n", msg);
            out.println(msg);

            String response = in.readLine();
            System.out.printf("Got response %s\n", response);

        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error while communicating with server", null, null, e);
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't shutdown connection with server gracefully", null, null, e);
            }
        }
    }

    public static void main(String[] args) {
        Utente utente = new Utente("antolab");
        utente.richiesta();
    }
}
