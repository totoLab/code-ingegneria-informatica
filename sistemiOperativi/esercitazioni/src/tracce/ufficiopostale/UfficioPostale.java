package tracce.ufficiopostale;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class UfficioPostale {

    int numeroImpiegatiPerTipo;

    UfficioPostale(int numeroImpiegatiPerTipo) {
        this.numeroImpiegatiPerTipo = numeroImpiegatiPerTipo;
    }

    abstract boolean ritiraTicket(String operazione) throws InterruptedException;

    abstract void attendiSportello(String operazione) throws InterruptedException;

    abstract void prossimoCliente() throws InterruptedException;

    abstract void eseguiOperazione() throws InterruptedException;

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
                codice = "A"; break;
            case 1:
                codice = "B"; break;
            case 2:
                codice = "C"; break;
            default:
                codice = "Cliente"; break;
        }
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("[%d] %s: %s", id, codice, msg);
        System.out.println(finalMsg);
    }

    void test(int numeroClienti) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < numeroImpiegatiPerTipo; i++) {
                new Impiegato(this, j).start();
            }
        }
        for (int i = 0; i < numeroClienti; i++) {
            new Cliente(this).start();
        }
    }
}
