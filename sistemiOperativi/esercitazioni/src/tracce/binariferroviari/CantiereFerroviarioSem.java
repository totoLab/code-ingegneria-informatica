package tracce.binariferroviari;

import java.util.concurrent.Semaphore;

public class CantiereFerroviarioSem extends CantiereFerroviario {

    private Semaphore semaforoTraverse;
    private Semaphore semaforoRotaie;

    public CantiereFerroviarioSem(int M) {
        super(M);
        this.semaforoTraverse = new Semaphore(M); // Binari inizialmente disponibili per le traverse
        this.semaforoRotaie = new Semaphore(0); // Rotaie inizialmente in attesa
    }

    @Override
    public void lavora(int t) throws InterruptedException {
        if (t == 0) { // Operaio di tipo traverse
            semaforoTraverse.acquire();
            System.out.println("Operaio traverse lavora su un binario.");
        } else { // Operaio di tipo rotaie
            semaforoRotaie.acquire();
            System.out.println("Operaio rotaie lavora su un binario.");
        }
    }

    @Override
    public void termina(int t) {
        if (t == 0) { // Completato il posizionamento della traversa, libera il semaforo per le rotaie
            semaforoRotaie.release();
            System.out.println("Operaio traverse ha terminato. Binario pronto per rotaia.");
        } else { // Completato il posizionamento della rotaia, libera il semaforo per la traversa
            semaforoTraverse.release();
            System.out.println("Operaio rotaie ha terminato. Binario pronto per traversa.");
        }
    }

    public static void main(String[] args) {
        CantiereFerroviario cantiere = new CantiereFerroviarioSem(3);
        cantiere.test(4, 4);
    }
}
