package es5.lettori_scrittori;

import java.util.concurrent.Semaphore;

public class MemoriaCondivisaSem extends MemoriaCondivisa {

    private int readers = 0;

    private Semaphore modifyStateMutex = new Semaphore(1);
    private Semaphore lock = new Semaphore(1);

    @Override
    public void inizioScrittura() throws InterruptedException {
        lock.acquire();
    }

    @Override
    public void fineScrittura() throws InterruptedException {
        lock.release();
    }

    @Override
    public void inizioLettura() throws InterruptedException {
        modifyStateMutex.acquire();
        if (readers == 0) {
            lock.acquire();
        }
        readers++;
        modifyStateMutex.release();
    }

    @Override
    public void fineLettura() throws InterruptedException {
        modifyStateMutex.acquire();
        readers--;
        if (readers == 0) {
            lock.release();
        }
        modifyStateMutex.release();
    }

    public static void main(String[] args) {
        MemoriaCondivisaSem m = new MemoriaCondivisaSem();
        m.test(5, 5);
    }
}
