package tracce.prontosoccorso;

import tracce.palazzo.Squadra;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class ProntoSoccorso {

    int numeroMedici;

    ProntoSoccorso(int numeroMedici) {
        this.numeroMedici = numeroMedici;
    }

    abstract void iniziaVisita() throws InterruptedException;

    abstract void terminaVisita() throws InterruptedException;

    abstract void accediPaziente() throws InterruptedException;

    abstract void esciPaziente() throws InterruptedException;

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void attesa(int t1, int t2) {
        Random random = new Random();
        attesa(random.nextInt(t1, t2));
    }

    void log(String msg, int tipo) {
        String codice = null;
        switch (tipo) {
            case 0:
                codice = "Verde "; break;
            case 1:
                codice = "Giallo"; break;
            case 2:
                codice = "Rosso "; break;
        }
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("[%d] %s: %s", id, codice, msg);
        System.out.println(finalMsg);
    }

    void test(int numeroPazienti) {
        for (int i = 0; i < numeroMedici; i++) {
            new Medico(this, i).start();
        }
        for (int i = 0; i < numeroPazienti; i++) {
            new Paziente(this).start();
        }
    }
}
