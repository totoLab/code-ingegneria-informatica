package esercitazione1.esercizio1_2;

import java.io.*;
import java.net.*;
public class SendObject {
    public static void main (String args[]) {
        try {
            ServerSocket server = new ServerSocket (3575);
            Socket client = server.accept();
            ObjectOutputStream output =
                    new ObjectOutputStream (client.getOutputStream ());
            output.writeObject("<Welcome>");
            Studente studente =
                    new Studente (14520,"Leonardo","da Vinci","Ingegneria Informatica");
            output.writeObject(studente);
            output.writeObject("<Goodbye>");
            client.close();
            server.close();
        } catch (Exception e) { System.err.println (e); }
    }
}
