package esercitazione1.esercizio1_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SendObjectAdv {

    private static HashMap<Integer, Studente> studenti = new HashMap();

    public static void main(String[] args) {
        preFill();
        ServerSocket ss;
        try {
            ss = new ServerSocket(2121);
            System.out.println("Server runnning");
            while (true) {
                Socket client = ss.accept();
                System.out.println("Accepted client " + client);

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                pw.println("Inserire matricola: ");

                String M = in.readLine();
                System.out.println("Got string: " + M);
                if (null != M) {
                    Studente studente = getStudente(M.trim());
                    System.out.println("Got object: " + studente);
                    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                    if (null != studente) {
                        out.writeObject("<Welcome>");
                        out.writeObject(studente);
                        out.writeObject("<Goodbye>");
                    } else {
                        out.writeObject("Siamo spiacenti ma non è presente nessuno studente con la matricola inviata");
                    }
                }
                client.close();
            }
            // ss.close();
        } catch (IOException e) { System.out.println(e); }
    }

    private static void preFill() {

        ArrayList<Studente> s = new ArrayList<>();

        s.add(new Studente(123456, "Mario", "Rossi", "Ingegneria"));
        s.add(new Studente(789012, "Luigi", "Bianchi", "Economia"));
        s.add(new Studente(345678, "Giovanna", "Verdi", "Lettere"));

        for (Studente studente: s) {
            studenti.put(studente.getMatricola(), studente);
        }
        System.out.println("Filled data");
    }

    private static Studente getStudente(String matricola) {
        int numericM = Integer.parseInt(matricola);
        return studenti.getOrDefault(numericM, null);
    }
}
