package tracce.palazzo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PalazzoSem extends Palazzo {


    Semaphore[] possoLavorare = new Semaphore[] { new Semaphore(1), new Semaphore(0), new Semaphore(0) };
    Semaphore mutex = new Semaphore(1);
    int pianoInLavorazione = 0;
    int squadraDaRichiamare = 0;
    PalazzoSem(int numeroPiani) {
        super(numeroPiani);
    }

    void lavoro(int t) throws InterruptedException {
        attesa(oreDiLavoro[t]);
        mutex.acquire();
        if (t == 2) {
            squadraDaRichiamare = 0;
            Random random = new Random();
            int voto = random.nextInt(1, 101);
            if (voto > 89) {
                log("Richiamare entrambe le squadre", 2);
            } else if (voto >= 81) {
                log("Richiamare squadra pulizia (1)", 2);
                squadraDaRichiamare = 1;
            } else {
                log(String.format("Il piano %d va bene", pianoInLavorazione), 2);
                pianoInLavorazione += 1;
            }
        }
        mutex.release();
    }

    @Override
    void start(int t) throws InterruptedException {
        if (numeroPiani == pianoInLavorazione) {
            finito = true;
            return;
        }

        log("Sono arrivato", t);
        possoLavorare[t].acquire();
        log("Sto lavorando al piano " + pianoInLavorazione, t);
        lavoro(t);
    }

    @Override
    void end(int t) throws InterruptedException {
        if (t == 2) {
            possoLavorare[squadraDaRichiamare].release();
        } else {
            log("Lascio il posto alla prossima squadra", t);
            possoLavorare[(t + 1) % 3].release();
        }
        log("Riposo", t);
        attesa(8);
    }

    public static void main(String[] args) {
        Palazzo palazzo = new PalazzoSem(1);
        palazzo.test();
    }
}
