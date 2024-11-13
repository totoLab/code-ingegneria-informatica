package es5.cinque_filosofi;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TavoloLCPriority extends Tavolo {


    class ThreadAged extends Thread implements Comparable<ThreadAged> {

        Thread t;
        int accesses = 0;

        public ThreadAged(Thread thread) {
            this.t = thread;
        }

        void setAccesses(int accesses) {
            this.accesses = accesses;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof ThreadAged)) return false;

            ThreadAged other = (ThreadAged) obj;
            return t.equals(other.t);
        }

        @Override
        public int compareTo(ThreadAged o) {
            return accesses - o.accesses;
        }
    }

    TreeSet<ThreadAged> threadAccesses = new TreeSet<>();

    private Lock lock = new ReentrantLock();
    private Condition[] bacchette = new Condition[NUM_FILOSOFI];
    private boolean[] bacchettaLibera = new boolean[NUM_FILOSOFI];

    TavoloLCPriority() {
        for (int i = 0; i < NUM_FILOSOFI; i++) {
            bacchette[i] = lock.newCondition();
            bacchettaLibera[i] = true;
        }
    }

    boolean mioTurno() {
        return Thread.currentThread().equals(threadAccesses.first().t);
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        lock.lock();
        try {
            ThreadAged t = new ThreadAged(Thread.currentThread());
            threadAccesses.add(t);

            while (!(bacchettaLibera[i] && bacchettaLibera[(i + 1) % NUM_FILOSOFI] && !mioTurno())) {
                if (!bacchettaLibera[i]) bacchette[i].await();
                else bacchette[(i + 1) % NUM_FILOSOFI].await();
            }

            t.setAccesses(t.accesses + 1);
            threadAccesses.add(t);

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
        TavoloLCPriority tavolo = new TavoloLCPriority();
        tavolo.test();
    }
}
