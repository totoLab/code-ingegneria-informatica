package es5.bounded_buffer;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BufferSem extends Buffer {

    Semaphore postiVuoti = new Semaphore(getCapacity());
    Semaphore elementiDisponibili = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);

    BufferSem(int capacity) {
        super(capacity);
    }

    @Override
    void put(int i) throws InterruptedException {
        postiVuoti.acquire();
        mutex.acquire();
        buffer[in] = i;
        in = (in + 1) % buffer.length;
        mutex.release();
        elementiDisponibili.release();
    }

    @Override
    int get() throws InterruptedException {
        elementiDisponibili.acquire();
        mutex.acquire();
        int temp = buffer[out];
        out = (out + 1) % buffer.length;
        mutex.release();
        postiVuoti.release();
        return temp;
    }

    public static void main(String[] args) {
        BufferSem buffer = new BufferSem(12);
        buffer.test(5, 5);
    }
}
