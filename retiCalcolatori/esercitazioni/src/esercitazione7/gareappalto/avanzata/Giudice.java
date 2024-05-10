package esercitazione7.gareappalto.avanzata;

import esercitazione7.gareappalto.base.Offerta;
import esercitazione7.gareappalto.base.Richiesta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.TimeUnit;

import static utils.Logging.Type;
import static utils.Logging.print;

public class Giudice {

    final int TCP_PORT_ENTE = 2000;
    final String ipBroadcast = "230.0.0.1";
    final int UDP_PORT_BROADCAST = 3000;
    final int TCP_PORT_PARTECIPANTE = 4000;

    private Socket ente;
    private Richiesta richiestaCorrente;
    private Offerta offertaMigliore;
    final private MulticastSender ms;

    Giudice() {
        ms = new MulticastSender();
    }

    void acceptEnteRequests() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(TCP_PORT_ENTE);
            while (true) {
                print(Type.INFO, "Accepting new requests...", null, null, null);
                this.ente = ss.accept();
                print(Type.INFO, "Accepted new client " + ente.getInetAddress(), null, null, null);

                ObjectInputStream ois = new ObjectInputStream(ente.getInputStream());

                try {
                    richiestaCorrente = (Richiesta) ois.readObject();
                    print(Type.INFO, "Got the request", null, null, null);

                    broadcastRequest(richiestaCorrente);
                    acceptPartecipanteOffers();
                    communicateWinner();
                } catch (ClassNotFoundException e) {
                    print(Type.ERROR, "Couldn't ", null, this, e);
                }
                this.ente.close();
            }
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't communicate correctly with client", null, this, e);
        }

    }

    void broadcastRequest(Richiesta richiesta) {
        ms.setContent(richiesta.toString());
        ms.setActive(true);
        ms.start();
    }

    void acceptPartecipanteOffers() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(TCP_PORT_PARTECIPANTE);
            ss.setSoTimeout(15 * 1000);
            boolean done = false;
            while (!done) {
                Socket partecipante = ss.accept();

                ObjectInputStream ois = new ObjectInputStream(partecipante.getInputStream());

                Offerta offerta = (Offerta) ois.readObject();
                if (offerta != null) {
                    print(Type.INFO, "Got new offer " + offerta.getImportoRichiesto(), null, null, null);

                    if (offertaMigliore == null) offertaMigliore = offerta;
                    else if (offerta.getImportoRichiesto() < offertaMigliore.getImportoRichiesto()) {
                        offertaMigliore = offerta;
                    }
                }

            }
        } catch (SocketTimeoutException e) {
            print(Type.INFO, "Offering time has finished", null, this, e);
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't communicate correctly with client", null, this, e);
        } catch (ClassNotFoundException e) {
            print(Type.ERROR, "Couldn't cast object", null, this, e);
        } finally {
            try {
                if (ss != null) ss.close();
            } catch (IOException e) { print(Type.ERROR, "Couldn't gracefully close ServerSocket", null, this, e); }
        }
    }

    private void communicateWinner() {
        String winner = "Winning offer: " + offertaMigliore.getId() + " - " + offertaMigliore.getImportoRichiesto();

        try {
            print(Type.INFO, "Sending results to " + ente.getInetAddress(), null, this, null);
            PrintWriter pw = new PrintWriter(this.ente.getOutputStream(), true);
            pw.println(winner);
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't send results back", null, this, e);
        }

        print(Type.INFO, "Changing broadcast message", null, this, null);
        ms.setContent(winner);
        ms.setActive(true);
        try {
            int timeout = 43;
            print(Type.INFO, "Started countdown (" + timeout +") to shutdown broadcast", null, this, null);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            print(Type.ERROR, "Thread error, ignoring", null, this, e);
        }
        ms.setActive(false);
    }

    class MulticastSender extends Thread {

        private String content;
        private boolean active = true;

        public synchronized void setActive(boolean active) {
            this.active = active;
        }

        public synchronized boolean isActive() {
            return active;
        }

        public synchronized void setContent(String content) {
            this.content = new String(content);
        }

        @Override
        public void run() {
            MulticastSocket multicast;
            try {
                multicast = new MulticastSocket();
                byte[] buf;
                print(Type.INFO, "Starting broadcast...", null, this, null);
                while (isActive()) {
                    buf = content.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(ipBroadcast), UDP_PORT_BROADCAST);
                    TimeUnit.SECONDS.sleep(2);
                    multicast.send(packet);
                }
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't communicate in multicast", null, this, e);
            } catch (InterruptedException e) {
                print(Type.ERROR, "Thread couldn't sleep", null, this, e);
            }
        }
    }

    public static void main(String[] args) {
        Giudice giudice = new Giudice();
        giudice.acceptEnteRequests();
    }

}
