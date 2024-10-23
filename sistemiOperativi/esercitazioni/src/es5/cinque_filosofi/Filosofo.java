package es5.cinque_filosofi;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosofo extends Thread {
    private Tavolo tavolo;
    private int posizione;
    private Random random = new Random();

    public Filosofo(Tavolo t, int pos) {
        tavolo = t;
        posizione = pos;
    }

    public void run() {
        try {
            while (true) {
                tavolo.prendiBacchette(posizione);
                // System.out.format("Il filosofo %d ha iniziato a mangiare%n", posizione);
                mangia();
                // System.out.format("Il filosofo %d ha finito di mangiare%n", posizione);
                tavolo.rilasciaBacchette(posizione);
                pensa();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void mangia() {
        attendi(5, 10);
    }

    void pensa() {
        attendi(15, 30);
    }

    void attendi(int min, int max) {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(min, max));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
