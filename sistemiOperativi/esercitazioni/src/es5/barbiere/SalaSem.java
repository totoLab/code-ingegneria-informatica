package es5.barbiere;

import java.util.concurrent.Semaphore;

public class SalaSem extends Sala {

    Semaphore mutex = new Semaphore(1);
    Semaphore clientiDisponibili = new Semaphore(0, true);
    Semaphore poltrona =  new Semaphore(0, true);

    public SalaSem(int sedie) {
        super(sedie);
    }

    @Override
    public void tagliaCapelli() throws InterruptedException {
        clientiDisponibili.acquire();
        poltrona.release();
    }

    @Override
    public boolean attendiTaglio() throws InterruptedException {
        mutex.acquire();
        if (sedieLibere == 0) { // se tutto occupato se ne va
            mutex.release();
            return false;
        }
        sedieLibere--;
        mutex.release();
        clientiDisponibili.release();
        poltrona.acquire(); // barbiere si "addormenta" sulla poltrona

        mutex.acquire();
        sedieLibere++; // libera la sedia
        mutex.release();
        return true;
    }

    public static void main(String[] args) {
        Sala s = new SalaSem(5);
        try {
            s.test(30);
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}
