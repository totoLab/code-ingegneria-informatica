package es5.cinque_filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSemDeaklock extends Tavolo {

    Semaphore[] bacchette = new Semaphore[NUM_FILOSOFI];

    TavoloSemDeaklock() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        bacchette[i].acquire();
        bacchette[(i + 1) % NUM_FILOSOFI].acquire();
    }

    @Override
    public void rilasciaBacchette(int i) {
        bacchette[i].release();
        bacchette[(i + 1) % NUM_FILOSOFI].release();
    }

    public static void main(String[] args) {
        Tavolo tavolo = new TavoloSemDeaklock();
        tavolo.test();
    }
}
