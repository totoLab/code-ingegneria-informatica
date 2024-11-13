package es5.cinque_filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSemSlow extends Tavolo {

    Semaphore[] bacchette = new Semaphore[NUM_FILOSOFI];
    Semaphore turno = new Semaphore(1);

    TavoloSemSlow() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        turno.acquire();
        bacchette[i].acquire();
        bacchette[(i + 1) % NUM_FILOSOFI].acquire();
    }

    @Override
    public void rilasciaBacchette(int i) {
        bacchette[i].release();
        bacchette[(i + 1) % NUM_FILOSOFI].release();
        turno.release();
    }

    public static void main(String[] args) {
        Tavolo tavolo = new TavoloSemSlow();
        tavolo.test();
    }
}
