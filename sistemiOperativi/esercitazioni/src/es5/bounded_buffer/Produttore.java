package es5.bounded_buffer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Produttore extends Thread {

    Buffer buffer;
    int n_operations;

    Produttore(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (!isInterrupted()) {
                int number = random.nextInt(1, 10 * buffer.getCapacity());
                buffer.put(number);
                n_operations++;
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {}
    }
}
