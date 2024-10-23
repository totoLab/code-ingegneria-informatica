package es5.lettori_scrittori;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MemoriaCondivisaLC extends MemoriaCondivisa {

    final private Lock lock = new ReentrantLock();
    final private Condition canRead = lock.newCondition(), canWrite = lock.newCondition();
    private boolean oneIsWriting = false;
    private int readers = 0;

    @Override
    public void inizioScrittura() throws InterruptedException {
        lock.lock();
        try {
            while (readers > 0 || oneIsWriting) {
                canWrite.await();
            }
            oneIsWriting = true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void fineScrittura() {
        lock.lock();
        try {
            oneIsWriting = false;
            canRead.signalAll();
            canWrite.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void inizioLettura() throws InterruptedException {
        lock.lock();
        try {
            while (oneIsWriting) {
                canRead.await();
            }
            readers += 1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void fineLettura() {
        lock.lock();
        try {
            readers -= 1;
            if (readers == 0) {
                canWrite.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MemoriaCondivisa mc = new MemoriaCondivisaLC();
        mc.test(5, 5);
    }
}
