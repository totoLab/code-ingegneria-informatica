package es5.bounded_buffer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public abstract class Buffer {

    protected int[] buffer;
    protected int in = 0, out = 0;

    Buffer(int capacity) {
        buffer = new int[capacity];
    }

    public int getCapacity() {
        return buffer.length;
    }

    abstract void put(int i) throws InterruptedException;

    abstract int get() throws InterruptedException;

    public void test(int numProduttori, int numConsumatori) {
        Produttore[] p = new Produttore[numProduttori];
        Consumatore[] c = new Consumatore[numConsumatori];

        for (int i = 0; i < numConsumatori; i++) {
            c[i] = new Consumatore(this);
            c[i].start();
        }
        for (int i = 0; i < numProduttori; i++) {
            p[i] = new Produttore(this);
            p[i].start();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numProduttori; i++) {
            p[i].interrupt();
        }

        for (int i = 0; i < numConsumatori; i++) {
            c[i].interrupt();
        }

        try {
            for (int i = 0; i < numProduttori; i++) {
                p[i].join();
            }

            for (int i = 0; i < numConsumatori; i++) {
                c[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (int i = 0; i < numProduttori; i++) {
            total += p[i].n_operations;
        }

        for (int i = 0; i < numConsumatori; i++) {
            total -= c[i].n_operations;
        }

        System.out.printf("%s: operations' total is %d.%n", total >= 0 ? "Ok": "Error", total);
        System.out.println(Arrays.toString(buffer));
    }
}
