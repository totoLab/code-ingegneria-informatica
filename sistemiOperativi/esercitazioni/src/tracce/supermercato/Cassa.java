package tracce.supermercato;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Cassa {

    final int numeroCasse;

    public Cassa(int numeroCasse) {
        this.numeroCasse = numeroCasse;
    }

    abstract void svuotaCarrello(int N) throws InterruptedException;

    abstract void scansiona() throws InterruptedException;

    abstract void paga(int N) throws InterruptedException;

    abstract void prossimoCliente() throws InterruptedException;

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void attesa(int t1, int t2) {
        Random random = new Random();
        attesa(random.nextInt(t1, t2));
    }

    void log(String msg) {
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("[%d] %s", id, msg);
        System.out.println(finalMsg);
    }

    void test(int numeroClienti) {
        for (int i = 0; i < numeroCasse; i++) {
            new Cassiere(this, i).start();
        }
        for (int i = 0; i < numeroClienti; i++) {
            new Cliente(this).start();
        }
    }
}
