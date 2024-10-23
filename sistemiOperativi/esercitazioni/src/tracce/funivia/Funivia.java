package tracce.funivia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

abstract public class Funivia {

    abstract void pilotaStart() throws InterruptedException;

    abstract void pilotaEnd() throws InterruptedException;

    abstract void turistaSali(int t) throws InterruptedException;

    abstract void turistaScendi(int t) throws InterruptedException;

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(3 * t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    static long getId() {
        return Thread.currentThread().getId();
    }

    void test(int aPiedi, int inBici) {
        Pilota pilota = new Pilota(this);
        pilota.start();
        for (int i = 0; i < aPiedi; i++) {
            new Turista(this, 0).start();
        }
        for (int i = 0; i < inBici; i++) {
            new Turista(this, 1).start();
        }
    }
}
