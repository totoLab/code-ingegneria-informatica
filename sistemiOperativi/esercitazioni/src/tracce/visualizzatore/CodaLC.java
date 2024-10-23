package tracce.visualizzatore;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaLC extends Coda {

    Lock l = new ReentrantLock();
    Condition possoAggiungere = l.newCondition();
    Condition possoPrelevare = l.newCondition();

    @Override
    void inserisci(int n) throws InterruptedException {
        l.lock();
        try {
            System.out.printf("t%d trying to add %d%n", Thread.currentThread().getId(), n);
            while (capacity - size < n) {
                possoAggiungere.await();
            }

            for (int i = 0; i < n; i++) {
                String toAdd = String.format("t%d adding %d/%d", Thread.currentThread().getId(), i + 1, n);
                System.out.println(toAdd);
                coda.add(toAdd);
                size++;
                possoPrelevare.signal();
                TimeUnit.SECONDS.sleep(2);
            }

        } finally {
            l.unlock();
        }
    }

    @Override
    void preleva() throws InterruptedException {
        l.lock();
        try {
            while (size == 0) {
                possoPrelevare.await();
            }
            size--;
            String first = coda.remove();
            System.out.println(String.format("Got '%s'", first));
            possoAggiungere.signalAll();
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Coda coda = new CodaLC();
        coda.test(10);
    }
}
