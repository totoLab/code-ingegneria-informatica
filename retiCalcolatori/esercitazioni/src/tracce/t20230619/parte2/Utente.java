package tracce.t20230619.parte2;

import tracce.t20230619.parte2schifo.Sondaggio;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class Utente {

    class RisposteSondaggio implements Serializable {
        List<String> domande;
        List<String> risposte;
    }

    void sondaggiDisponibili() {
        new Thread(() -> {
            MulticastSocket ms = null;
            try {
                ms = new MulticastSocket(5000);
                InetAddress group = InetAddress.getByName("230.0.0.1");
                ms.joinGroup(group);
                byte[] buf = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ms.receive(packet);
                // TODO select id using certain criteria
                ms.leaveGroup(group);

                int id = 0;
                joinSondaggio(id);
            } catch (IOException e) {

            }
        }).start();
    }

    private void joinSondaggio(int id) {
        try {
            Socket server = new Socket(InetAddress.getLocalHost(), 4000);
            PrintWriter pw = new PrintWriter(server.getOutputStream(), true);
            pw.println(id);
            ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
            tracce.t20230619.parte2schifo.Sondaggio sondaggio = (tracce.t20230619.parte2schifo.Sondaggio) ois.readObject();
            if (sondaggio.getDomande().isEmpty()) {
                System.out.println("Sondaggio terminato");
            } else {
                RisposteSondaggio risposte = risposteUtente(sondaggio);
                ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                oos.writeObject(risposte);
                oos.flush();
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    private RisposteSondaggio risposteUtente(Sondaggio sondaggio) {
        Random random = new Random();
        RisposteSondaggio risposteSondaggio = new RisposteSondaggio();
        for (String domanda : sondaggio.getDomande()) {
            risposteSondaggio.domande.add(domanda);
            String risposta = random.nextBoolean() ? "SI" : "NO";
            risposteSondaggio.risposte.add(risposta);
        }
        return risposteSondaggio;
    }

    public static void main(String[] args) {
        Utente utente = new Utente();
        utente.sondaggiDisponibili();
    }
}
