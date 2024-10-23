package es5.bounded_buffer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLCFifo extends Buffer {

    private int elements = 0;
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private LinkedList<Thread> queueProduttori = new LinkedList<>();
    private LinkedList<Thread> queueConsumatori = new LinkedList<>();

    public BufferLCFifo(int capacity) {
        super(capacity);
    }

    private boolean canPut() {
        return elements < buffer.length &&
                Thread.currentThread().equals(queueProduttori.getFirst());
    }

    @Override
    void put(int i) throws InterruptedException {
        lock.lock();
        try {
            queueProduttori.add(Thread.currentThread());
            while (!canPut()) {
                full.await();
            }
            queueProduttori.remove();

            buffer[in] = i;
            in = (in + 1) % buffer.length;

            elements++;
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean canGet() {
        return elements > 0 &&
                Thread.currentThread().equals(queueConsumatori.getFirst());
    }

    @Override
    int get() throws InterruptedException {
        lock.lock();
        try {
            queueConsumatori.add(Thread.currentThread());
            while (!canGet()) {
                empty.await();
            }
            queueConsumatori.remove();

            int temp = buffer[out];
            out = (out + 1) % buffer.length;

            elements--;
            full.signalAll();
            return temp;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new BufferLCFifo(12);
        buffer.test(5, 5);
    }

}
