package es5.cinque_filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSem extends Tavolo {

    Semaphore[] bacchette = new Semaphore[NUM_FILOSOFI];
    Semaphore turno = new Semaphore(1);

    TavoloSem() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        turno.acquire();
        bacchette[i].acquire();
        bacchette[(i + 1) % NUM_FILOSOFI].acquire();
        turno.release();
    }

    @Override
    public void rilasciaBacchette(int i) {
        bacchette[i].release();
        bacchette[(i + 1) % NUM_FILOSOFI].release();
    }

    public static void main(String[] args) {
        Tavolo tavolo = new TavoloSem();
        tavolo.test();
    }
}
