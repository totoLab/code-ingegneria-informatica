package tracce.palazzo;

import tracce.callcenter.CallCenter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Palazzo {

    final int[] oreDiLavoro = new int[] { 20, 10, 5 };
    final int numeroPiani;

    boolean finito = false;

    boolean eFinito() {
        return finito;
    }


    Palazzo(int numeroPiani) {
        this.numeroPiani = numeroPiani;
    }

    abstract void start(int t) throws InterruptedException;

    abstract void end(int t) throws InterruptedException;

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void attesa(int t1, int t2) {
        try {
            Random random = new Random();
            TimeUnit.SECONDS.sleep(random.nextInt(t1, t2));
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void log(String msg, int tipo) {
        String squadra = null;
        switch (tipo) {
            case 0:
                squadra = "Costruzione"; break;
            case 1:
                squadra = "Pulizia    "; break;
            case 2:
                squadra = "Controllo  "; break;
        }
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("%s: %s", squadra, msg);
        System.out.println(finalMsg);
    }

    void test() {
        for (int i = 0; i < 3; i++) {
            new Squadra(this, i).start();
        }
    }

}
