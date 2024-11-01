package tracce.casello;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread {

    Random random = new Random();
    Casello casello;
    final int T = 1;

    Veicolo(Casello casello) {
        this.casello = casello;
    }

    @Override
    public void run() {
        try {
            int chilometri = random.nextInt(50, 100);
            casello.log("Percorrendo " + chilometri + "km fino al casello");
            percorri(chilometri);
            int porta = scegliPorta(casello.numeroPorte);
            casello.accedi(porta, chilometri);
            casello.esci(porta);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    int scegliPorta(int porte) {
        return random.nextInt(porte);
    }

    void percorri(int km) {
        attesa(km * T);
    }

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void attesa(int t1, int t2) {
        int t = random.nextInt(t1, t2);
        attesa(t);
    }
}
