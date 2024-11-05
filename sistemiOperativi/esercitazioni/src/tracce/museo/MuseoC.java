package tracce.museo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class MuseoC {

    abstract void visitaSA() throws InterruptedException;

    abstract void terminaVisitaSA() throws InterruptedException;

    abstract void visitaSD() throws InterruptedException;

    abstract void terminaVisitaSD() throws InterruptedException;

    void log(String msg) {
        String finalMsg = String.format("%d: %s", Thread.currentThread().getId(), msg);
        System.out.println(finalMsg);
    }

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void attesa(int t1, int t2) {
        Random random = new Random();
        int t = random.nextInt(t1, t2);
        attesa(t);
    }

    void test(int numeroVisitatori) {
        for (int i = 0; i < numeroVisitatori; i++) {
            new Visitatore(this).start();
        }
    }

}
