package es5.bounded_buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLC extends Buffer {

    private int elements = 0;
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();


    BufferLC(int capacity) {
        super(capacity);
    }

    @Override
    void put(int i) throws InterruptedException {
        lock.lock();
        try {
            while (elements == buffer.length) {
                full.await();
            }

            buffer[in] = i;
            in = (in + 1) % buffer.length;

            elements++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    int get() throws InterruptedException {
        lock.lock();
        try {
            while (elements == 0) {
                empty.await();
            }

            int temp = buffer[out];
            out = (out + 1) % buffer.length;

            elements--;
            full.signal();
            return temp;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Buffer bufferLC = new BufferLC(12);
        bufferLC.test(5, 5);
    }
}
