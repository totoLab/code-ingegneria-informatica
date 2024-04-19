package esercitazione4.bet.slides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class BetClient {
    private int serverPort;
    private int myPort;
    private InetAddress groupAddress;
    private InetAddress serverAddress;
    private Socket s;

    public BetClient(InetAddress gAddress, InetAddress server, int sPort, int mPort) {
        groupAddress = gAddress;
        serverAddress = server;
        serverPort = sPort;
        myPort = mPort;
        try {
            s = new Socket(serverAddress, serverPort);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public boolean placeBet(int nCavallo, long puntata) {
        String e = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String bet = nCavallo + " " + puntata;
            out.println(bet); // invia la scommessa
            e = in.readLine(); // riceve un msg di risposta
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return e.equals("Scommessa accettata.");
    }

    public void riceviElencoVincitori() {
        try {
            MulticastSocket socket = new MulticastSocket(myPort);
            socket.joinGroup(groupAddress);
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String elenco = new String(packet.getData());
            System.out.print("Elenco vincitori: ");
            System.out.println(elenco);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void main(String[] args) {
        int serverPort = 8001;
        int myPort = 8002;
        try {
            InetAddress group = InetAddress.getByName("230.0.0.1");
            InetAddress server = InetAddress.getByName("127.0.0.1");
            BetClient client = new BetClient(group, server, serverPort, myPort);
            int cavallo = ((int) (Math.random() * 12)) + 1; // cavallo su cui scommette
            int cifra = ((int) (Math.random() * 100)) + 1; // cifra su cui scommette
            if (client.placeBet(cavallo, cifra))
                client.riceviElencoVincitori();
        } catch (UnknownHostException uhe) {
            System.out.println(uhe);
        }
    }
}

