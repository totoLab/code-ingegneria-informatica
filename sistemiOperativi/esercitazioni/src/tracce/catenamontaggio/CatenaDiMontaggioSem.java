package tracce.catenamontaggio;

import java.util.concurrent.Semaphore;

public class CatenaDiMontaggioSem extends CatenaDiMontaggioA {

    Semaphore[] produzioni = new Semaphore[] { new Semaphore(0), new Semaphore(0) };
    Semaphore[] puoAssemblare = new Semaphore[] { new Semaphore(0), new Semaphore(0) };
    Semaphore mutex = new Semaphore(1);
    int[] pezziRimanenti = new int[] { 0, 0 };

    @Override
    void richiediProduzione(int pezziSx, int pezziDx) throws InterruptedException {
        log(String.format("Nuova richiesta (%d, %d)", pezziSx, pezziDx));
        mutex.acquire();
        produzioni[0].release(pezziSx);
        pezziRimanenti[0] += pezziSx;
        produzioni[1].release(pezziDx);
        pezziRimanenti[1] += pezziDx;
        mutex.release();
    }

    @Override
    void produci(int tipo) throws InterruptedException {
        log("In attesa di richiesta per pezzo di tipo " + tipo);
        produzioni[tipo].acquire();
        log("Producendo pezzo di tipo " + tipo);
        attesa(10 + tipo * 5);
        mutex.acquire();
        pezziRimanenti[tipo] -= 1;
        if (pezziRimanenti[tipo] == 0) {
            puoAssemblare[tipo].release();
        }
        mutex.release();
    }

    @Override
    void assembla() throws InterruptedException {
        puoAssemblare[0].acquire();
        puoAssemblare[1].acquire();
        log("Risorse acquisite, inizio assemblaggio...");
        attesa(20);
        log("Fine assemblaggio.");
    }

    public static void main(String[] args) {
        CatenaDiMontaggioA catenaDiMontaggioA = new CatenaDiMontaggioSem();
        catenaDiMontaggioA.test(3, 4);
    }
}
