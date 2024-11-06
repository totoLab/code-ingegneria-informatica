package tracce.supermercato;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class CassaSem2 extends Cassa {

    Semaphore mutex = new Semaphore(1);
    int[] prodotti;
    Semaphore[] prossimoCliente;
    Semaphore[] puoPagare;
    Semaphore[] pagato;
    Semaphore[] puoScansionare;
    int[] personeInFila;

    Map<Thread, Integer> clientiCassa = new HashMap<>();

    CassaSem2(int numeroCasse) {
        super(numeroCasse);
        this.prodotti = new int[numeroCasse];
        this.prossimoCliente = new Semaphore[numeroCasse];
        this.puoPagare = new Semaphore[numeroCasse];
        this.pagato = new Semaphore[numeroCasse];
        this.puoScansionare = new Semaphore[numeroCasse];
        this.personeInFila = new int[numeroCasse];
        for (int i = 0; i < numeroCasse; i++) {
            prodotti[i] = 0;
            prossimoCliente[i] = new Semaphore(1);
            puoPagare[i] = new Semaphore(0);
            pagato[i] = new Semaphore(0);
            puoScansionare[i] = new Semaphore(0);
            personeInFila[i] = 0;
        }
    }

    void scegliCassa() {
        int ret = 0;
        int min = personeInFila[ret];
        for (int i = 1; i < numeroCasse; i++) {
            if (personeInFila[i] < min) {
                min = personeInFila[i];
                ret = i;
            }
        }
        clientiCassa.put(Thread.currentThread(), ret);
    }

    int getCassa() {
        return clientiCassa.get(Thread.currentThread());
    }

    int getCassiere() {
        return ((Cassiere) Thread.currentThread()).id;
    }

    @Override
    void svuotaCarrello(int N) throws InterruptedException {
        log("Buongiorno");
        mutex.acquire();
        scegliCassa();
        mutex.release();
        prossimoCliente[getCassa()].acquire();
        log("Svuoto il carrello");
        prodotti[getCassa()] += N;
        log("Finito di svuotare il carrello");
        puoScansionare[getCassa()].release();
    }

    @Override
    void scansiona() throws InterruptedException {
        puoScansionare[getCassiere()].acquire();
        log("Comincio a scansionare...");
        attesa(2 * prodotti[getCassiere()]);
        log("Fine scansione, può pagare");
        puoPagare[getCassiere()].release();
    }

    @Override
    void paga(int N) throws InterruptedException {
        log("Attendo di pagare");
        puoPagare[getCassa()].acquire();
        log("Importo: €" + 5 * N);
        pagato[getCassa()].release();
    }

    @Override
    void prossimoCliente() throws InterruptedException {
        pagato[getCassiere()].acquire();
        log("Prossimo cliente");
        prossimoCliente[getCassiere()].release();
    }

    public static void main(String[] args) {
        Cassa cassa = new CassaSem2(2);
        cassa.test(5);
    }
}
