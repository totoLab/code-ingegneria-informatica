package es5.cinque_filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSemMancino extends Tavolo {

    Semaphore[] bacchette = new Semaphore[NUM_FILOSOFI];

    TavoloSemMancino() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        if (i == 4) {
            bacchette[0].acquire();
            bacchette[4].acquire();
        } else {
            bacchette[i].acquire();
            bacchette[(i + 1) % NUM_FILOSOFI].acquire();
        }
    }

    @Override
    public void rilasciaBacchette(int i) {
        if (i == 4) {
            bacchette[0].release();
            bacchette[4].release();
        } else {
            bacchette[i].release();
            bacchette[(i + 1) % NUM_FILOSOFI].release();
        }

    }

    public static void main(String[] args) {
        Tavolo tavolo = new TavoloSemMancino();
        tavolo.test();
    }
}
