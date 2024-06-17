package esercitazione10.esercizio1.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static utils.Logging.Type;
import static utils.Logging.print;

public class Intermediario {

    int TCP_CLIENTE = 2345;
    int UDP_VENDITORI = 6789;
    List<Venditore> venditori;

    Intermediario(List<Venditore> venditori) {
        this.venditori = new ArrayList<>(venditori);
    }

    public void avvia() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(TCP_CLIENTE);
            while (true) {
                Socket socket = serverSocket.accept();
                new RichiestaHandler(socket);
            }
        } catch (IOException e) {
            print(Type.ERROR, "Couldn't connect to client correctly", null, null, null);
        }
    }

    class RichiestaHandler extends Thread {

        private Risposta rispostaMinima;

        Socket cliente;

        RichiestaHandler(Socket socket) {
            this.cliente = socket;
        }

        @Override
        public void run() {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(cliente.getInputStream());
                Richiesta richiesta = (Richiesta) ois.readObject();
                for (Venditore venditore : venditori) {
                    sendTo(venditore, richiesta);
                }
                TimeUnit.SECONDS.sleep(60);
                if (rispostaMinima == null) {
                    rispostaMinima = new Risposta(
                            richiesta.getIdProdotto(),
                            richiesta.getQuantita(),
                            -1, -1);
                }
                PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);
                pw.println(rispostaMinima);
            } catch (IOException e) {
                print(Type.ERROR, "Couldn't get stream correctly", null, null, null);
            } catch (ClassNotFoundException e) {
                print(Type.ERROR, "Couldn't receive object correctly", null, null, null);
            } catch (InterruptedException e) {
                print(Type.ERROR, "Thread stopped", null, null, null);
            }
        }

        private void sendTo(Venditore venditore, Richiesta richiesta) {
            new Thread(() -> {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket();
                    byte[] buf = richiesta.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, venditore.getInetAddress(), UDP_VENDITORI);
                    socket.send(packet);

                    socket = new DatagramSocket();
                    buf = new byte[1024];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String response = new String(packet.getData()).trim();
                    Risposta risposta = new Risposta(response);
                    updateRispostaMinima(risposta);

                } catch (IOException e) {
                    print(Type.ERROR, "Couldn't send package correctly", null, null, null);
                } finally {
                    if (socket != null) socket.close();
                }
            }).start();
        }

        private synchronized void updateRispostaMinima(Risposta risposta) {
            if (rispostaMinima == null) rispostaMinima = risposta;
            if (risposta.getPrezzoTotale() < rispostaMinima.getPrezzoTotale()) {
                rispostaMinima = risposta;
            }
        }
    }

    public static void main(String[] args) {
        Map<Venditore, Thread> map = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            Venditore v = new Venditore();
            Runnable venditoreThread = () -> {
                Random rand = new Random();

                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(6789);
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String request = new String(packet.getData()).trim();
                    String[] parts = request.split(",");
                    String idProdottoToParse = parts[0];
                    String quantitaToParse = parts[1];
                    int idProdotto = Integer.parseInt(idProdottoToParse);
                    int quantita = Integer.parseInt(quantitaToParse);
                    Richiesta richiesta = new Richiesta(idProdotto, quantita);

                    double price = rand.nextDouble(richiesta.getQuantita(), richiesta.getQuantita() * 10);
                    Risposta risposta = new Risposta(price, finalI, richiesta.getQuantita(), richiesta.getIdProdotto());

                    buf = risposta.toString().getBytes();
                    packet = new DatagramPacket(buf, buf.length);
                    socket.send(packet);

                } catch (IOException e) {
                    print(Type.ERROR, "Couldn't send package correctly", null, currentThread(), null);
                } finally {
                    if (socket != null) socket.close();
                }
            };
            Thread thread = new Thread(venditoreThread);
            map.put(v, thread);
        }
        List<Venditore> venditori = new ArrayList<>(map.keySet());
        Intermediario intermediario = new Intermediario(venditori);
        intermediario.avvia();
        map.values().stream().forEach(t -> t.start());
    }
}
