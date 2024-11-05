package tracce.palazzo;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PalazzoLC extends Palazzo {

    Lock l = new ReentrantLock();
    Condition possoLavorare = l.newCondition();
    boolean finito = false;

    int prossimoTurno = 0;
    int pianoInLavorazione = 0;

    PalazzoLC(int numeroPiani) {
        super(numeroPiani);
    }

    boolean mioTurno(int t) {
        return prossimoTurno == t;
    }

    void lavoro(int t) throws InterruptedException {
        attesa(oreDiLavoro[t]);
    }

    @Override
    void start(int t) throws InterruptedException {
        if (numeroPiani == pianoInLavorazione + 1) {
            finito = true;
            return;
        }

        log("Sono arrivato", t);
        l.lock();
        try {
            while (!mioTurno(t)) {
                possoLavorare.await();
            }

            log("Sto lavorando al piano " + pianoInLavorazione, t);
            lavoro(t);

            log("Lascio il posto alla prossima squadra", t);
            if (t == 2) {
                prossimoTurno = 0;
                Random random = new Random();
                int voto = random.nextInt(1, 101);
                if (voto > 89) {
                    log("Richiamare entrambe le squadre", 2);
                } else if (voto >= 81) {
                    log("Richiamare squadra pulizia (1)", 2);
                    prossimoTurno = 1;
                } else {
                    log(String.format("Il piano %d va bene", pianoInLavorazione), 2);
                    pianoInLavorazione += 1;
                }
            } else {
                prossimoTurno += 1;
            }

            possoLavorare.signalAll();
        } finally {
            l.unlock();
        }
    }

    @Override
    void end(int t) throws InterruptedException {
        log("Riposo", t);
        attesa(8);
    }

    public static void main(String[] args) {
        Palazzo palazzo = new PalazzoLC(50);
        palazzo.test();
    }
}
