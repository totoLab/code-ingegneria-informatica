package tracce.t20220712.parte2;

import utils.Logging;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {
    // Store the class name as an Object for the logging calls
    private static final Object CLASS_NAME = Client.class;

    @Override
    public void run() {
        Random random = new Random();
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(Server.BROADCAST_PORT, InetAddress.getByName("230.0.0.1"));
            // Logging.print(type, message, tag, object, throwable)
            Logging.print(Logging.Type.INFO,
                    "Client successfully started and listening for broadcasts",
                    "run", CLASS_NAME, null);

            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String msg = new String(packet.getData());
                String[] parts = msg.split("-");
                Richiesta richiesta;
                int port;
                try {
                    port = Integer.parseInt(parts[0].trim());
                    int id = Integer.parseInt(parts[1].trim());
                    double prezzo = Double.parseDouble(parts[2].trim());
                    int sconto = Integer.parseInt(parts[3].trim());
                    int pezzi = Integer.parseInt(parts[4].trim());
                    richiesta = new Richiesta(id, pezzi);
                } catch (Exception e) {
                    Logging.print(Logging.Type.ERROR,
                            "Failed to parse broadcast message: " + msg.trim(),
                            "run", CLASS_NAME, e);
                    continue;
                }

                try {
                    InetAddress address = InetAddress.getByName("192.168.1.25");
                    Socket server = new Socket(address, port);
                    Logging.print(Logging.Type.INFO,
                            "Successfully connected to server on port: " + port,
                            "run", CLASS_NAME, null);

                    ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                    out.writeObject(richiesta);
                    out.flush();

                    BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    String response = in.readLine();
                    Logging.print(Logging.Type.INFO,
                            "Purchase completed successfully: " + response,
                            "run", CLASS_NAME, null);

                    parts = response.split(":");
                    parts = parts[1].split(", ");
                    int ordine = Integer.parseInt(parts[0]);
                    int prezzo = Integer.parseInt(parts[2]);

                    if (random.nextInt(prezzo) < prezzo / 2) {
                        new Thread(() -> {
                            try {
                                Socket serverResi = new Socket(address, Server.RESI_PORT);
                                PrintWriter pw = new PrintWriter(serverResi.getOutputStream(), true);
                                pw.println(ordine);

                                BufferedReader in2 = new BufferedReader(new InputStreamReader(serverResi.getInputStream()));
                                String refundAmount = in2.readLine();
                                Logging.print(Logging.Type.INFO,
                                        String.format("Return processed for order %d with refund amount: %s",
                                                ordine, refundAmount),
                                        "processReturn", CLASS_NAME, null);
                            } catch (IOException e) {
                                Logging.print(Logging.Type.ERROR,
                                        "Failed to process return for order: " + ordine,
                                        "processReturn", CLASS_NAME, e);
                                throw new RuntimeException(e);
                            }
                        }).start();
                    }
                } catch (IOException e) {
                    Logging.print(Logging.Type.ERROR,
                            "Failed to communicate with server",
                            "run", CLASS_NAME, e);
                }

                TimeUnit.SECONDS.sleep(30);
            }
        } catch (IOException | InterruptedException e) {
            Logging.print(Logging.Type.ERROR,
                    "Critical client error - shutting down",
                    "run", CLASS_NAME, e);
        }
    }

    public static void main(String[] args) {
        Logging.print(Logging.Type.INFO,
                "Starting client application",
                "main", CLASS_NAME, null);
        Client client = new Client();
        client.start();
    }
}