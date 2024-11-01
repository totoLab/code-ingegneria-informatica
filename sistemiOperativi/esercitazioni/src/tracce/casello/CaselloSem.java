package tracce.casello;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CaselloSem extends Casello {

    int incasso;
    Semaphore[] code = new Semaphore[numeroPorte];
    Semaphore mutex = new Semaphore(1);

    CaselloSem(int numeroPorte, int tariffa) {
        super(numeroPorte, tariffa);
        for (int i = 0; i < numeroPorte; i++) {
            code[i] = new Semaphore(1, true);
        }
    }

    @Override
    void accedi(int porta, int km) throws InterruptedException {
        log("In coda al casello per la porta " + porta);
        code[porta].acquire();
        log("È il mio turno al casello per la porta " + porta);

        int importo = km * tariffa;
        mutex.acquire();
        log("Pagando la tariffa di €" + importo);
        incasso += importo;
        mutex.release();
        attesa(3, 6); // paga
    }

    @Override
    void esci(int porta) {
        log("Uscendo dal casello per la porta " + porta);
        attesa(3); // paga
        code[porta].release();
    }


    public static void main(String[] args) {
        Casello casello = new CaselloSem(7, 4);
        casello.test(15);
    }
}
