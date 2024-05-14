package esercitazione7.gareappalto.registrogare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.TimeUnit;

import static utils.Logging.Type;
import static utils.Logging.print;

public class GiudiceMultigara {

    final int TCP_PORT_ENTE = 2000;
    final String ipBroadcast = "230.0.0.1";
    final int UDP_PORT_BROADCAST = 3000;
    final int TCP_PORT_PARTECIPANTE = 4000;

    final RegistroGare registroGare;

    GiudiceMultigara() {
        this.registroGare = new RegistroGare();
    }

    void avvia() {
        new RichiesteHandler().start();
        new OfferteHandler().start();
    }

    class RichiesteHandler extends Thread {
        ServerSocket serverSocket;

        RichiesteHandler() {
            try {
                serverSocket = new ServerSocket(TCP_PORT_ENTE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            print(Type.INFO, "Accepting connections...", null, this, null);
            while (true) {
                try {
                    Socket ente = serverSocket.accept();
                    print(Type.INFO, "Accepted new actor " + ente.getInetAddress(), null, this, null);

                    ObjectInputStream ois = new ObjectInputStream(ente.getInputStream());
                    new Thread(() -> {
                        Richiesta richiesta = null;
                        try {
                            richiesta = (Richiesta) ois.readObject();
                            print(Type.INFO, "Received object " + richiesta, null, this, null);

                            int idGara = registroGare.addGara(ente, richiesta);
                            new TimeoutHandler(idGara).start();

                        } catch (IOException e) {
                            print(Type.ERROR, "Couldn't receive object", null, currentThread(), e);
                        } catch (ClassNotFoundException e) {
                            print(Type.ERROR, "Couldn't cast received object", null, currentThread(), e);
                        }
                    }).start();
                } catch (IOException e) {
                    print(Type.ERROR, "Communication with client failed", null, this, e);
                }
            }
        }
    }

    class OfferteHandler extends Thread {

        ServerSocket serverSocket;

        OfferteHandler() {
            try {
                serverSocket = new ServerSocket(TCP_PORT_PARTECIPANTE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            print(Type.INFO, "Accepting offers...", null, this, null);
            while (true) {
                try {
                    Socket partecipante = serverSocket.accept();
                    print(Type.INFO, "Accepted " + partecipante.getInetAddress(), null, this, null);

                    ObjectInputStream ois = new ObjectInputStream(partecipante.getInputStream());
                    PrintWriter pw = new PrintWriter(partecipante.getOutputStream(), true);

                    Offerta offerta = (Offerta) ois.readObject();
                    String message;
                    if (registroGare.addOfferta(offerta)) {
                        message = "Offerta accettata";
                    } else {
                        message = "Offerta rifiutata";
                    }
                    pw.println(message);
                    print(Type.INFO, message + " " + partecipante.getInetAddress(), null, this, null);

                } catch (IOException e) {
                    print(Type.ERROR, "Communication with client failed", null, this, e);
                } catch (ClassNotFoundException e) {
                    print(Type.ERROR, "Couldn't cast received object", null, this, e);
                }
            }
        }
    }

    class TimeoutHandler extends Thread {

        int idGara;
        int timeout = 60;

        TimeoutHandler(int idGara) {
            this.idGara = idGara;
        }

        @Override
        public void run() {
            Richiesta richiesta = null;
            try {
                richiesta = registroGare.getRichiesta(idGara);
            } catch (NullPointerException e) {
                return;
            }

            broadcast(idGara, richiesta.toString());
            try {
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            registroGare.closeGara(idGara);
            Offerta offertaMigliore = registroGare.getOffertaMigliore(idGara);
            String winner = offertaMigliore != null ? offertaMigliore.toString() + "migliore" : "Nessuna offerta ricevuta";
            Socket ente = registroGare.getEnte(idGara);
            broadcast(idGara, winner);
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(ente.getOutputStream(), true);
                pw.println(winner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void broadcast(int idGara, String content) {
            MulticastSocket multicast;
            try {
                multicast = new MulticastSocket();
                byte[] buf;
                print(Type.INFO, "Starting broadcast #" + idGara + "...", null, this, null);
                buf = content.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(ipBroadcast), UDP_PORT_BROADCAST);
                multicast.send(packet);
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't communicate in multicast", null, this, e);
            }
        }
    }


    public static void main(String[] args) {
        GiudiceMultigara giudice = new GiudiceMultigara();
        giudice.avvia();
    }
}

