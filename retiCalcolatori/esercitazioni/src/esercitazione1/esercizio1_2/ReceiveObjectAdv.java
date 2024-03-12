package esercitazione1.esercizio1_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveObjectAdv {
    public static void main (String[] args) {
        try {
            Socket socket = new Socket ("localhost",2121);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner keyboard = new Scanner(System.in);
            String id = keyboard.next();
            out.println(id);

            ObjectInputStream input =
                    new ObjectInputStream (socket.getInputStream ());
            String beginMessage = (String)input.readObject();
            System.out.println (beginMessage);
            Studente studente = (Studente)input.readObject();
            System.out.print (studente.getMatricola()+" - ");
            System.out.print (studente.getNome()+" "+studente.getCognome()+" - ");
            System.out.print (studente.getCorsoDiLaurea()+"\n");
            String endMessage = (String)input.readObject();
            System.out.println (endMessage);
            socket.close();
        } catch (Exception e) { System.err.println (e); }
    }
}
