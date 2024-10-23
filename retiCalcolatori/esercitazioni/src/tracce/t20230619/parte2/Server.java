package tracce.t20230619.parte2;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Server {

    final int TCP_RICEZIONE = 3000;
    final int TCP_PARTECIPAZIONE = 4000;
    final int UDP_COMUNICAZIONE = 5000;
    final String IP_COMUNICAZIONE = "230.0.0.1";

    GestioneSondaggi gs = new GestioneSondaggi();

    void ricezione() {
        new Thread(() -> {
            ServerSocket serverSondaggi = null;
            try {
                serverSondaggi = new ServerSocket(TCP_RICEZIONE);
                while (true) {
                    Socket azienda = serverSondaggi.accept();
                    new Thread(() -> {
                        try {
                            ObjectInputStream ois = new ObjectInputStream(azienda.getInputStream());
                            Sondaggio sondaggio = (Sondaggio) ois.readObject();
                            int id = gs.addSondaggio(azienda, sondaggio);
                            broadcast(id + "#" + sondaggio.getNome());
                        } catch (IOException e) {

                        } catch (ClassNotFoundException e) {

                        }
                    }).start();
                }
            } catch (IOException e) {

            }
        }).start();
    }

    void broadcast(String data) {
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket();
            byte[] buf = data.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(IP_COMUNICAZIONE), UDP_COMUNICAZIONE);
            ms.send(packet);
        } catch (IOException e) {

        }
    }

    void ricezioneRisposte(LocalTime start) {
        new Thread(() -> {
            ServerSocket serverSondaggi = null;
            try {
                serverSondaggi = new ServerSocket(TCP_PARTECIPAZIONE);
                while (true) {
                    Socket utente = serverSondaggi.accept();
                    new Thread(() -> {
                        try {
                            BufferedReader bf = new BufferedReader(new InputStreamReader(utente.getInputStream()));
                            Integer id = Integer.getInteger(bf.readLine());
                            GestioneSondaggi.SondaggioCompleto sondaggioRichiesto = gs.getSondaggio(id);

                            ObjectOutputStream oosUtente = new ObjectOutputStream(utente.getOutputStream());
                            if(sondaggioRichiesto.terminato()) {
                                // risposta all'utente
                                Sondaggio sondaggio = new Sondaggio("", new LinkedList<>());
                                oosUtente.writeObject(sondaggio);

                                // comunicazione all'azienda
                                Socket azienda = sondaggioRichiesto.getAzienda();
                                ObjectOutputStream oosAzienda =  new ObjectOutputStream(azienda.getOutputStream());
                                Map<String, HashMap<Boolean, Integer>> risposte = gs.getRisposte(sondaggioRichiesto);
                                oosAzienda.writeObject(risposte);
                            } else {
                                oosUtente.writeObject(sondaggioRichiesto.sondaggio);
                                ObjectInputStream ois = new ObjectInputStream(utente.getInputStream());
                                RispostaSondaggio risposteSondaggio = (RispostaSondaggio) ois.readObject();
                                gs.addRisposte(sondaggioRichiesto.sondaggio, risposteSondaggio);
                            }

                        } catch (IOException e) {

                        } catch (ClassNotFoundException e) {

                        }
                    }).start();
                }

            } catch (IOException e) {

            }
        }).start();
    }

}
