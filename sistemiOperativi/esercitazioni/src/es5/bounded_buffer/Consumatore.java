package es5.bounded_buffer;

import java.util.concurrent.TimeUnit;

public class Consumatore extends Thread {

    Buffer buffer;
    int n_operations;

    Consumatore(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                buffer.get();
                n_operations++;
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {}
    }
}
