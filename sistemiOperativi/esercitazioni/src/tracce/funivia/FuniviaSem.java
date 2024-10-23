package tracce.funivia;

import java.util.concurrent.Semaphore;

public class FuniviaSem extends Funivia {

    Semaphore[] posti = new Semaphore[] { new Semaphore(6), new Semaphore(0)};
    Semaphore cabinaPiena =  new Semaphore(0);
    Semaphore cabinaVuota = new Semaphore(0);

    Semaphore[] valle = new Semaphore[] { new Semaphore(6), new Semaphore(0)};
    Semaphore cima = new Semaphore(0);

    Semaphore mutex = new Semaphore(1);
    int postiOccupati = 0;
    int tipoCorrente = 0;

    @Override
    void pilotaStart() throws InterruptedException {
        System.out.println("Attesa turisti...");
        cabinaPiena.acquire();
        // sale
        System.out.println("Funivia piena, in partenza");
        attesa(5);
        System.out.println("Funivia in cima");
    }

    @Override
    void pilotaEnd() throws InterruptedException {
        cima.release(6);
        System.out.println("Discesa turisti...");
        cabinaVuota.acquire();
        // scende
        System.out.println("Funivia vuota, in partenza");
        attesa(2);
        System.out.println("Funivia a valle");
        valle[1 - tipoCorrente].release(6);
    }

    @Override
    void turistaSali(int t) throws InterruptedException {
        valle[t].acquire(1 + t);
        posti[t].acquire(1 + t);

        mutex.acquire();
        postiOccupati += 1 + t;
        System.out.println(getId() + " salito sulla funivia " + (t == 0 ? "a piedi" : "in bici") + " (" + postiOccupati + "/6)");
        if (postiOccupati == 6) cabinaPiena.release();
        mutex.release();
    }

    @Override
    void turistaScendi(int t) throws InterruptedException {
        cima.acquire(1 + t);
        posti[1 - t].release(1 + t);

        mutex.acquire();
        tipoCorrente = t;
        postiOccupati -= 1 + t;
        System.out.println(getId() + " sceso dalla funivia " + (t == 0 ? "a piedi" : "in bici") + " (" + postiOccupati + "/6)");
        if (postiOccupati == 0) cabinaVuota.release();
        mutex.release();
    }

    public static void main(String[] args) {
        Funivia funivia = new FuniviaSem();
        funivia.test(18, 9);
    }

}
