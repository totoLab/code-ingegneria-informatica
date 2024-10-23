package es5.cinque_filosofi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TavoloLC extends Tavolo {

    private Lock lock = new ReentrantLock();
    private Condition[] bacchette = new Condition[NUM_FILOSOFI];
    private boolean[] bacchettaLibera = new boolean[NUM_FILOSOFI];

    TavoloLC() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = lock.newCondition();
            bacchettaLibera[i] = true;
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        lock.lock();
        try {
            while (!(bacchettaLibera[i] && bacchettaLibera[(i + 1) % NUM_FILOSOFI])) {
                if (!bacchettaLibera[i]) bacchette[i].await();
                else bacchette[(i + 1) % NUM_FILOSOFI].await();
            }

            bacchettaLibera[i] = false;
            bacchettaLibera[(i + 1) % NUM_FILOSOFI] = false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void rilasciaBacchette(int i) {
        lock.lock();
        try {
            bacchettaLibera[i] = true;
            bacchettaLibera[(i + 1) % NUM_FILOSOFI] = true;

            bacchette[i].signal();
            bacchette[(i + 1) % NUM_FILOSOFI].signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TavoloLC tavolo = new TavoloLC();
        tavolo.test();
    }
}
