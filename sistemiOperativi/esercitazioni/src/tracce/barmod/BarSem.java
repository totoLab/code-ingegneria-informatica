package tracce.barmod;

import java.util.concurrent.Semaphore;

public class BarSem extends Bar {

    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] fila = new Semaphore[NUM_FILE];

    BarSem() {
        super();
        fila[0] = new Semaphore(postiMax[0], true);
        fila[1] = new Semaphore(postiMax[1], true);
    }

    @Override
    int scegli() throws InterruptedException {
        mutex.acquire();
        int bestFila;
        if (personeInFila[0] == 0) bestFila = 0;
        else if (personeInFila[1] == 0) bestFila = 1;
        else if (personeInFila[0] == personeInFila[1]) bestFila = 0;
        else {
            bestFila = 0;
            for (int i = 1; i < NUM_FILE; i++) {
                int n = personeInFila[i];
                if (personeInFila[bestFila] > n) {
                    bestFila = i;
                }
            }
        }
        mutex.release();
        return bestFila;
    }

    @Override
    void inizia(int i) throws InterruptedException {
        mutex.acquire();
        System.out.println(getId() + ": In fila al" + (i == 0 ? "la cassa" : " bancone"));
        personeInFila[i]++;
        mutex.release();

        fila[i].acquire();
        System.out.println(getId() + ": Preso il posto al" + (i == 0 ? "la cassa" : " bancone"));
        attesa(i);
    }

    @Override
    void finisci(int i) throws InterruptedException {
        mutex.acquire();
        System.out.println(getId() + ": Libero la fila al" + (i == 0 ? "la cassa" : " bancone"));
        personeInFila[i]--;
        mutex.release();

        System.out.println(getId() + ": Finito al" + (i == 0 ? "la cassa" : " bancone"));
        fila[i].release();
    }

    public static void main(String[] args) {
        BarSem bar = new BarSem();
        bar.test(10);
    }
}
