package esercitazione7.gareappalto.slides;


import java.io.*;
import java.net.*;

public class GiudiceMultiGara {
    private final static RegistroGare registro = new RegistroGare();
    public void avvia() {
        try {
// Fase 1-2: ricevi richiesta e invio a partecipanti
            new ThreadRichiesteHandler(registro).start();
// Fase 3: ricevi offerte dai partecipanti
            new ThreadOfferteHandler(registro).start();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static void main(String[] args) {
        new GiudiceMultiGara().avvia();
    }
}

class ThreadRichiesteHandler extends Thread {
    private ServerSocket socket;
    private RegistroGare registro;
    private final static int rPort = 2000;
    public ThreadRichiesteHandler(RegistroGare registro) throws IOException {
        this.registro = registro;
        this.socket = new ServerSocket(rPort);
    }
    public void run() {
        try {
            while (true) {
                Socket socketRichiesta = socket.accept();
                ObjectInputStream ois = new
                        ObjectInputStream(socketRichiesta.getInputStream());
                Richiesta richiesta = (Richiesta) ois.readObject();
                System.out.println("Ricevuta nuova richiesta: " + richiesta);
// Aggiungo la richiesta di gara al registro
                Gara gara = new Gara(socketRichiesta, richiesta);
                int idGara = registro.addGara(gara);
// Avvio thread per gestire il timeout sulle offerte per questa gara
                new ThreadTimeoutHandler(idGara, registro).start();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class ThreadOfferteHandler extends Thread {
    private final RegistroGare registro;
    private ServerSocket socket;
    private final static int oPort = 4000;
    public ThreadOfferteHandler(RegistroGare registro) {
        this.registro = registro;
    }
    public void run() {
        try {
            this.socket = new ServerSocket(oPort);
            while (true) {
                Socket partecipante = socket.accept();
                ObjectInputStream ois = new
                        ObjectInputStream(partecipante.getInputStream());
                Offerta offerta = (Offerta) ois.readObject();
                this.registro.aggiungiOfferta(offerta);
                partecipante.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTimeoutHandler extends Thread {
    private static final long TIMEOUT = 60000;
    private final static int gPort = 3000;
    private final static String gAddress = "230.0.0.1";
    private final int idGara;
    private final MulticastSocket mSocket;
    private final RegistroGare registro;

    public ThreadTimeoutHandler(int idGara, RegistroGare registro) throws IOException {
        this.registro = registro;
        this.idGara = idGara;
        this.mSocket = new MulticastSocket(gPort);
    }

    public void run() {
        try {
// Invio la richiesta a tutti i partecipanti
            inviaRichiestaAiPartecipanti(registro.getRichiesta(idGara));
// Attendo per un certo periodo l'arrivo delle offerte
            sleep(TIMEOUT);
// Allo scadere del timeout chiudo la gara
            this.registro.chiudiGara(idGara);
            System.out.println("Gara " + idGara + " chiusa!");
//Invio all'ente e ai partecipanti l'esito della gara
            inviaEsitoGara();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void inviaRichiestaAiPartecipanti(Richiesta richiesta) {
        try {
            String r = "RICHIESTA-" + richiesta.getIdEnte() + "-" +
                    richiesta.getDescrizione() + "-" +
                    richiesta.getImportoMassimo();
            byte buf[] = r.getBytes();
            InetAddress group = InetAddress.getByName(gAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
                    gPort);
            mSocket.send(packet);
            System.out.println("Inviata richiesta in multicast: " + richiesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inviaEsitoGara() {
        try { // Invio notifica ai partecipanti
            System.out.println("Ricerco nel registro gara " + idGara);
            Offerta oVincente = this.registro.getOffertaVincente(idGara);
            if (oVincente == null) oVincente = new Offerta(-1, idGara, -1);
            System.out.println(oVincente);
            String message = "ESITO-" + this.idGara + "-"
                    + oVincente.getIdPartecipante() + "-" +
                    oVincente.getImportoRichiesto();
            byte[] buf = message.getBytes();
            InetAddress group = InetAddress.getByName(gAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
                    gPort);
            mSocket.send(packet);
// Invio notifica all'ente
            ObjectOutputStream oos = new
                    ObjectOutputStream(this.registro.getSocketEnte(idGara).getOutputStream());
            oos.writeObject(oVincente);
            System.out.println("Inviata Offerta vincente all'ente");
            this.registro.getSocketEnte(idGara).close(); // Posso chiudere il socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}