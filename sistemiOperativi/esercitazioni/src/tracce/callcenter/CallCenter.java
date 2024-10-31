package tracce.callcenter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class CallCenter {

    abstract void richiediAssistenza() throws InterruptedException;

    abstract void fornisciAssistenza() throws InterruptedException;

    abstract void terminaChiamata() throws InterruptedException;

    abstract void prossimoCliente() throws InterruptedException;

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

    enum Tipo {OPERATORE, CLIENTE}

    void log(String msg, Tipo tipo) {
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("%s %d: %s", tipo.toString(), id, msg);
        System.out.println(finalMsg);
    }

    void test(int numeroOperatori, int numeroClienti) {
        for (int i = 0; i < numeroOperatori; i++) {
            new Operatore(this).start();
        }
        for (int i = 0; i < numeroClienti; i++) {
            new Cliente(this, i).start();
        }
    }
}
