package tracce.t20220614.parte2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Azienda {

    public static void main(String[] args) {
        String piva = "24971471012";
        Offerta offerta = new Offerta(
                Offerta.Settore.S1, "manager",
                Offerta.Tipo.IndeterminatoFulltime,
                100000.0, piva
        );
        try {
            InetAddress hostname = InetAddress.getByName("job.unical.it");
            Socket server = new Socket(hostname, 3000);
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(offerta);
            oos.flush();

            BufferedReader bf = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String response = bf.readLine();
            System.out.println("Offerta registrata con id: " + response);

            server.close();

            ServerSocket ricezioneCandidature = new ServerSocket(5000);
            while (true) {
                server = ricezioneCandidature.accept();
                ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
                Candidatura candidatura = (Candidatura) ois.readObject();
                System.out.println("Nuova candidatura: " + candidatura);
                server.close();
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }
}
