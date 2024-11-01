package tracce.casello;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Casello {

    final int numeroPorte;
    final int tariffa;

    Casello(int numeroPorte, int tariffa) {
        this.numeroPorte = numeroPorte;
        this.tariffa = tariffa;
    }

    abstract void accedi(int porta, int km) throws InterruptedException;

    abstract void esci(int porta);

    void log(String msg) {
        String finalMsg = String.format("%d: %s", Thread.currentThread().getId(), msg);
        System.out.println(finalMsg);
    }

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void attesa(int t1, int t2) {
        Random random = new Random();
        int t = random.nextInt(t1, t2);
        attesa(t);
    }


    void test(int veicoli) {
        for (int i = 0; i < veicoli; i++) {
            new Veicolo(this).start();
        }
    }
}
