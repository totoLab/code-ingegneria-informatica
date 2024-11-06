package tracce.supermercato;

import java.util.concurrent.Semaphore;

public class CassaSem extends Cassa {

    Semaphore mutex = new Semaphore(1);
    int prodotti = 0;
    Semaphore prossimoCliente = new Semaphore(1);
    Semaphore puoPagare = new Semaphore(0);
    Semaphore pagato = new Semaphore(0);
    Semaphore puoScansionare = new Semaphore(0);

    public CassaSem() {
        super(1);
    }

    @Override
    void svuotaCarrello(int N) throws InterruptedException {
        log("Buongiorno");
        prossimoCliente.acquire();
        log("Svuoto il carrello");
        mutex.acquire();
        prodotti += N;
        mutex.release();
        log("Finito di svuotare il carrello");
        puoScansionare.release();
    }

    @Override
    void scansiona() throws InterruptedException {
        puoScansionare.acquire();
        log("Comincio a scansionare...");
        attesa(5 * prodotti);
        log("Fine scansione, può pagare");
        puoPagare.release();
    }

    @Override
    void paga(int N) throws InterruptedException {
        log("Attendo di pagare");
        puoPagare.acquire();
        log("Importo: €" + 5 * N);
        pagato.release();
    }

    @Override
    void prossimoCliente() throws InterruptedException {
        pagato.acquire();
        log("Prossimo cliente");
        prossimoCliente.release();
    }

    public static void main(String[] args) {
        Cassa cassa = new CassaSem();
        cassa.test(3);
    }
}
