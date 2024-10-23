package tracce.cocktail;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarSem extends Bar {

    BarSem(int n) {
        super(n);
        tempoPreparazione = new int[] {1, 2};
        prezzo = new int[] {6, 8};
    }

    Random random = new Random();

    int clienti = 0;
    Semaphore fila = new Semaphore(-N + 1);
    LinkedList<Integer> ordinazioni = new LinkedList<>();
    int cocktailPronti = 0;

    Semaphore mutex = new Semaphore(1);
    Semaphore bevi = new Semaphore(0);

    Semaphore cassa = new Semaphore(1, true);
    int totale = 0;

    Semaphore chiudi = new Semaphore(0);

    @Override
    void ordinaCocktail(int tipo) throws InterruptedException {
        mutex.acquire();
        log("Ordino un cocktail " + tipo);
        ordinazioni.add(tipo);
        clienti++;
        mutex.release();
        fila.release();
    }

    @Override
    void preparaCocktail() throws InterruptedException {
        fila.acquire();
        for (Integer ordinazione : ordinazioni) {
            log("Preparo un cockatil " + ordinazione);
            prepara(ordinazione);
            cocktailPronti++;
        }
        log("Finito di preparare");
        bevi.release(cocktailPronti);
    }

    @Override
    void beviEpaga(int tipo) throws InterruptedException {
        bevi.acquire();
        log("Bevo");
        TimeUnit.SECONDS.sleep(random.nextInt(2, 5));

        log("Ora sono in fila");
        cassa.acquire();
        log("È il mio turno alla cassa.");

        mutex.acquire();
        int importo = prezzo[tipo];
        totale += importo;
        log("Pagato €" + importo);
        clienti--;
        if (clienti == 0) {
            chiudi.release();
        }
        mutex.release();

        cassa.release();
    }

    @Override
    void chiudiBar() throws InterruptedException {
        chiudi.acquire();
        log("Il Semaforo, incasso: " + totale);
    }

    void log(String msg) {
        long id = Thread.currentThread().getId();
        String finalMsg = String.format("%d: %s", id, msg);
        System.out.println(finalMsg);
    }

    public static void main(String[] args) {
        BarSem barSem = new BarSem(5);
        barSem.test();
    }
}
