package tracce.binariferroviari;

import java.util.Random;

public abstract class CantiereFerroviario {

    final protected int binariDisponibili;
    private final Random random = new Random();

    protected CantiereFerroviario(int m) {
        binariDisponibili = m;
    }

    abstract void lavora(int t) throws InterruptedException;

    abstract void termina(int t) throws InterruptedException;

    void test(int numOperaiTraverse, int numOperaiRotaie) {
        for (int i = 0; i < numOperaiTraverse; i++) {
            new Operaio(0, this, i).start();
        }

        // Creazione e avvio degli operai delle rotaie
        for (int i = 0; i < numOperaiRotaie; i++) {
            new Operaio(1, this, i).start();
        }
    }
}
