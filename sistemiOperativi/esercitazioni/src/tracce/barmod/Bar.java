package tracce.barmod;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public abstract class Bar {

    protected final int NUM_FILE = 2;
    protected int[] personeInFila = new int[NUM_FILE];
    protected final int[] postiMax = new int[]{1, 4};

    Bar() {
        Arrays.fill(personeInFila, 0);
    }

    abstract int scegli() throws InterruptedException;

    abstract void inizia(int i) throws InterruptedException;

    abstract void finisci(int i) throws InterruptedException;

    void attesa(int i) {
        Random rand = new Random();
        int attesa;
        if (i == 0) {
            attesa = rand.nextInt(5 ,10);
        } else {
            attesa = rand.nextInt(20, 40);
        }
        try {
            TimeUnit.SECONDS.sleep(attesa);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    static long getId() {
        return Thread.currentThread().getId();
    }

    public void test(int clienti) {
        for (int i = 0; i < clienti; i++) {
            new Cliente(this).start();
        }
    }
}
