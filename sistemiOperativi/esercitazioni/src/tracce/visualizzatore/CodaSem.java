package tracce.visualizzatore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CodaSem extends Coda {

    Semaphore canInsert = new Semaphore(capacity);
    Semaphore canDelete = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);


    @Override
    void inserisci(int n) throws InterruptedException {
        canInsert.acquire(n);
        mutex.acquire();
        System.out.printf("t%d trying to add %d%n", Thread.currentThread().getId(), n);

        for (int i = 0; i < n; i++) {
            String toAdd = String.format("t%d adding %d/%d", Thread.currentThread().getId(), i + 1, n);
            System.out.println(toAdd);
            coda.add(toAdd);
            size++;
            canDelete.release();
            TimeUnit.SECONDS.sleep(2);
        }

        mutex.release();
    }

    @Override
    void preleva() throws InterruptedException {
        canDelete.acquire();
        mutex.acquire();
        size--;
        String first = coda.remove();
        System.out.println(String.format("Got '%s'", first));
        mutex.release();
    }

    public static void main(String[] args) {
        Coda coda = new CodaSem();
        coda.test(10);
    }
}
