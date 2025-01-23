package tracce.t20190904.parte2;

import utils.Logging;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    final static String HOSTNAME = "clinica.unical.it";
    final static int PRENOTAZIONE_PORT = 3000;
    final static int ANNULLAMENTO_PORT = 4000;
    final static int STATISTICHE_PORT = 5000; // udp
    final InetAddress externalServer;

    // codice esame -> lista matricole
    final Map<String, List<String>> esamiMedici;

    // medico -> prenotazione
    Map<String, List<Prenotazione>> prenotazioni = new ConcurrentHashMap<>();

    // codice esame -> prenotazione
    Map<String, List<Prenotazione>> setPrenotazioni = new ConcurrentHashMap<>();

    // codice esame -> non prenotati
    Map<String, Integer> nonPrenotati = new ConcurrentHashMap<>();

    Map<String, Semaphore> coda = new ConcurrentHashMap<>();

    public Server(Map<String, List<String>> esamiMedici) {
        this.esamiMedici = new HashMap<>(esamiMedici);
        esamiMedici.keySet().stream().forEach(key -> {
            coda.put(key, new Semaphore(0));
        });

        try {
            externalServer = InetAddress.getByName("direzione.unical.it");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        richiestaPrenotazione();
        annullamentoPrenotazione();
        invioStatistiche();
    }

    class Prenotazione {
        static AtomicInteger ID_PROGRESSIVO = new AtomicInteger(0);

        int id;
        String codiceEsame;
        String medico;

        public Prenotazione(String codiceEsame, String medico) {
            this.codiceEsame = codiceEsame;
            this.medico = medico;
            this.id = ID_PROGRESSIVO.incrementAndGet();
        }
    }

    void richiestaPrenotazione() {
        new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(PRENOTAZIONE_PORT);
                while (true) {
                    Socket socket = server.accept();
                    new PrenotazioneHandler(socket).start();
                }
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't init server socket on port " + PRENOTAZIONE_PORT, "prenotazioni", this, e);
            }
        });
    }

    class PrenotazioneHandler extends Thread {

        Socket socket;
        LocalTime time = LocalTime.now();

        PrenotazioneHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                if (
                    time.isBefore(LocalTime.of(8, 0)) ||
                    time.isAfter(LocalTime.of(12, 0))
                ) {
                    out.println("service not available");
                    return;
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String codiceEsame = in.readLine();
                if (!esamiMedici.containsKey(codiceEsame)) {
                    out.println("Codice inesistente");
                    return;
                }

                for (String medico : esamiMedici.get(codiceEsame)) {
                    List<Prenotazione> prenotazioniMedico = prenotazioni.get(medico);
                    long prenotazioniCorrenti = prenotazioniMedico.stream()
                            .filter(o -> o.codiceEsame.equals(codiceEsame))
                            .count();
                    if (prenotazioniCorrenti < 10) {
                        Prenotazione prenotazione = new Prenotazione(codiceEsame, medico);
                        prenotazioniMedico.add(prenotazione);
                        setPrenotazioni.get(codiceEsame).add(prenotazione);
                    } else {
                        // inserimento in coda di attesa
                        boolean haAcquisito = coda.get(codiceEsame).tryAcquire(1, 1, TimeUnit.HOURS);
                        if (!haAcquisito) {
                            nonPrenotati.put(codiceEsame, nonPrenotati.get(codiceEsame) + 1);
                        }
                    }
                }

            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't communicate correctly with client " + socket.toString(), "prenotazioni", this, e);
            } catch (InterruptedException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't suspend thread correctly", "prenotazioni", this, e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    Logging.print(Logging.Type.ERROR, "Couldn't end connection gracefully " + socket.toString(), "prenotazioni", this, e);
                }
            }
        }
    }

    void annullamentoPrenotazione() {
        new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(ANNULLAMENTO_PORT);
                while (true) {
                    Socket socket = server.accept();
                    new AnnullamentoHandler(socket).start();
                }
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't init server socket on port " + ANNULLAMENTO_PORT, "annullamento", this, e);
            }
        });
    }

    class AnnullamentoHandler extends Thread {

        Socket socket;

        AnnullamentoHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String richiesta = in.readLine();

                String[] parts = richiesta.split("-");
                int codice = Integer.parseInt(parts[0]);
                String esame = parts[1];

                if (esamiMedici.containsKey(esame) && setPrenotazioni.containsKey(esame)) {
                    Prenotazione prenotazione = setPrenotazioni.get(esame).stream().filter(o -> o.id == codice).toList().getFirst();

                    List<Prenotazione> prenotazioniMedico = prenotazioni.get(prenotazione.medico);
                    prenotazioniMedico = prenotazioniMedico.stream().filter(o -> o.id == codice).toList();
                    prenotazioni.put(prenotazione.medico, prenotazioniMedico);

                    List<Prenotazione> prenotazioniEsame = setPrenotazioni.get(esame);
                    prenotazioniEsame = prenotazioniEsame.stream().filter(o -> o.id == codice).toList();
                    setPrenotazioni.put(esame, prenotazioniEsame);

                    coda.get(esame).release();
                }

            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't communicate correctly with client ", "annullamento", this, e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    Logging.print(Logging.Type.ERROR, "Couldn't end connection gracefully " + socket.toString(), "annullamento", this, e);
                }
            }
        }
    }

    void invioStatistiche() {
        new Thread(() -> {
            DatagramSocket external = null;
            try {
                LocalTime now;
                LocalTime endTime = LocalTime.of(12, 0);
                while (( now = LocalTime.now() ).isBefore(endTime)) {
                    long millisToWait = java.time.Duration.between(now, endTime).toMillis();
                    try {
                        Thread.sleep(millisToWait);
                    } catch (InterruptedException e) { }
                }

                external = new DatagramSocket();
                byte[] buf;
                DatagramPacket packet;

                for (String codiceEsame : setPrenotazioni.keySet()) {
                    List<Prenotazione> prenotazioniEsame = setPrenotazioni.get(codiceEsame);
                    Statistica statistica = new Statistica(codiceEsame, prenotazioniEsame.size(), nonPrenotati.get(codiceEsame));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(statistica);
                    oos.flush();

                    buf = baos.toByteArray();
                    packet = new DatagramPacket(buf, buf.length, externalServer, STATISTICHE_PORT);
                    external.send(packet);
                }
            } catch (IOException e) {
                Logging.print(Logging.Type.ERROR, "Couldn't init notification's server correctly", "notifica", this, e);
            } finally {
                if (external != null) {
                    external.close();
                }
            }
        }).start();
    }
}
