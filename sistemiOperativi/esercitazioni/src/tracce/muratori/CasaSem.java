package tracce.muratori;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class CasaSem extends Casa {

    int[] fileRimanenti = new int[NUM_PARETI];
    Semaphore mutex = new Semaphore(1);
    Semaphore[][] possoLavorare = new Semaphore[NUM_PARETI][2];
    int[] prossimoLavoro = new int[NUM_PARETI];

    Map<Thread, Integer> muratoriParete = new HashMap<>();

    CasaSem(int fileMax) {
        super(fileMax);
        Arrays.fill(fileRimanenti, this.fileMax);
        for (int i = 0; i < NUM_PARETI; i++) {
            possoLavorare[i][0] = new Semaphore(1);
            possoLavorare[i][1] = new Semaphore(0);
        }
        Arrays.fill(prossimoLavoro, 0);
    }

    boolean altroLavoro() {
        for (int i = 0; i < NUM_PARETI; i++) {
            if (fileRimanenti[i] != 0) return true;
        }
        return false;
    }

    int scegliParete(int t) {
        for (int i = 0; i < NUM_PARETI; i++) {
            if (fileRimanenti[i] > 0 && prossimoLavoro[i] == t) return i;
        }
        return -1; // non c'è più lavoro
    }

    @Override
    boolean inizia(int t) throws InterruptedException {
        log(t, "Sono arrivato");
        mutex.acquire();
        if (!altroLavoro()) {
            log(t, "Non c'è nenti");
            mutex.release();
            return false;
        }
        int parete = scegliParete(t);
        muratoriParete.put(Thread.currentThread(), parete);
        log(t, "Parete assegnata " + parete);
        mutex.release();
        log(t, "Comincio a preparare il materiale");
        attesa(preparazioneMateriale[t]);
        log(t, "Aspetto di poter lavorare alla parete " + parete);
        possoLavorare[parete][t].acquire();
        prossimoLavoro[parete] = 1 - t;
        return true;
    }

    @Override
    void termina(int t) throws InterruptedException {
        mutex.acquire();
        int parete = muratoriParete.get(Thread.currentThread());
        log(t, "Lascio il posto al mio collega per la parete " + parete);
        possoLavorare[parete][1 - t].release();
        muratoriParete.remove(Thread.currentThread());
        mutex.release();
    }

    public static void main(String[] args) {
        Casa casa = new CasaSem(20);
        casa.test(7, 5);
    }
}
