package tracce.visualizzatore;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utente extends Thread {

    Coda coda;
    private Random random = new Random();

    Utente(Coda coda) {
        this.coda = coda;
    }

    @Override
    public void run() {
        while (true) {
            int n = random.nextInt(1, 5);
            try {
                coda.inserisci(n);
                TimeUnit.SECONDS.sleep(random.nextInt(2, 7));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
