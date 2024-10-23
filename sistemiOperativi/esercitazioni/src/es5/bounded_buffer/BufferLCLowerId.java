package es5.bounded_buffer;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLCLowerId extends Buffer {

    private int elements = 0;
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private TreeMap<Long, Thread> queueProduttori = new TreeMap<>();
    private TreeMap<Long, Thread> queueConsumatori = new TreeMap<>();

    public BufferLCLowerId(int capacity) {
        super(capacity);
    }

    private boolean canPut() {
        return elements < buffer.length &&
                Thread.currentThread().equals(queueProduttori.firstEntry().getValue());
    }

    @Override
    void put(int i) throws InterruptedException {
        lock.lock();
        try {
            Long id = Thread.currentThread().getId();
            queueProduttori.put(id, Thread.currentThread());
            while (!canPut()) {
                full.await();
            }
            queueProduttori.remove(id);

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
                Thread.currentThread().equals(queueConsumatori.firstEntry().getValue());
    }

    @Override
    int get() throws InterruptedException {
        lock.lock();
        try {
            Long id = Thread.currentThread().getId();
            queueConsumatori.put(id, Thread.currentThread());
            while (!canGet()) {
                empty.await();
            }
            queueConsumatori.remove(id);

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
        Buffer buffer = new BufferLCLowerId(12);
        buffer.test(5, 5);
    }

}
