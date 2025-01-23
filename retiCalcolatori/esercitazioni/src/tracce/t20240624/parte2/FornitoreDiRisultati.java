package tracce.t20240624.parte2;

import utils.Logging;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FornitoreDiRisultati {

    String nome;

    FornitoreDiRisultati(String nome) {
        this.nome = nome;
    }

    void aggiornamento() {
        List<Partita> partite = new ArrayList<>();
        Random rand = new Random();
        Nazionale[] nazionali = Nazionale.values();
        for (int i = 0; i < 2; i++) {
            int index1 = rand.nextInt(nazionali.length);
            int index2 = rand.nextInt(nazionali.length);
            if (index1 == index2) {
                index2++;
                if (index2 >= nazionali.length) index2 = 0;
            }
            Partita partita = new Partita(
                    nazionali[index1].toString(), nazionali[index2].toString(),
                    LocalDate.now(), LocalTime.of(18, 0),
                    "Granillo", "Reggio Calabria");
            partite.add(partita);
        }

        MessaggioFornitore msg = new MessaggioFornitore(nome, partite);

        try {
            Socket server = new Socket(InetAddress.getLocalHost(), Server.SUPPLIERS_PORT);
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            out.writeObject(msg);
            out.flush();
            server.close();
        } catch (IOException e) {
            Logging.print(Logging.Type.ERROR, "Error while communicating with server", null, null, e);

        }
    }

    public static void main(String[] args) {
        FornitoreDiRisultati fornitore = new FornitoreDiRisultati("A");
        fornitore.aggiornamento();
    }
}
