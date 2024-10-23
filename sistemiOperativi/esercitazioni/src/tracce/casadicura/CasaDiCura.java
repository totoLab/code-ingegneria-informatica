package tracce.casadicura;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class CasaDiCura {

    final int MAX_SALA_PREPARAZIONE = 3;

    abstract void chiamaEIniziaOperazione() throws InterruptedException;

    abstract void fineOperazione() throws InterruptedException;

    abstract void pazienteEntra() throws InterruptedException;

    abstract void pazienteEsci() throws InterruptedException;

    long getId() {
        return Thread.currentThread().getId();
    }

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private Random random = new Random();
    void attesa(int t1, int t2) {
        int t = random.nextInt(t1, t2);
        attesa(t);
    }

    void test(int pazienti) {
        Medico medico = new Medico(this);
        medico.start();
        for (int i = 0; i < pazienti; i++) {
            new Paziente(this).start();
            attesa(5);
        }
    }

}
