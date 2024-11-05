package tracce.catenamontaggio;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class CatenaDiMontaggioA {

    abstract void richiediProduzione(int pezziSx, int pezziDx) throws InterruptedException;

    abstract void produci(int tipo) throws InterruptedException;

    abstract void assembla() throws InterruptedException;

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

    void test(int numeroSx, int numeroDx) {

        int[][] richieste = new int[][] {
                {2, 4},
                {4, 3},
                {5, 6}
        };
        Assemblatore assemblatore = new Assemblatore(this, richieste);
        assemblatore.setDaemon(true);
        assemblatore.start();

        for (int i = 0; i < numeroSx; i++) {
            new Produttore(this, 0).start();
        }
        for (int i = 0; i < numeroDx; i++) {
            new Produttore(this, 1).start();
        }
    }
}
