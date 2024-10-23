package tracce.casadicura;

import java.util.concurrent.Semaphore;

public class CasaDiCuraSem extends CasaDiCura {

    Semaphore salaPreparazione = new Semaphore(MAX_SALA_PREPARAZIONE);
    Semaphore salaOperazioneEntra = new Semaphore(0, true);
    Semaphore salaOperazioneEsci = new Semaphore(0);
    Semaphore pazienteDisponibile = new Semaphore(0);

    @Override
    void chiamaEIniziaOperazione() throws InterruptedException {
        System.out.println("Il prossimo!");
        pazienteDisponibile.acquire();
        salaOperazioneEntra.release();
        System.out.println("Comincia l'operazione...");
        attesa(20, 40);
    }

    @Override
    void fineOperazione() throws InterruptedException {
        System.out.println("Finisce l'operazione.");
        salaOperazioneEsci.release();
        System.out.println("Preparazione sala operatoria...");
        attesa(20);
        System.out.println("Fine preparazione.");
    }

    @Override
    void pazienteEntra() throws InterruptedException {
        System.out.println(getId() + ": In fila per la sala preparazione");
        salaPreparazione.acquire();
        pazienteDisponibile.release();
        System.out.println(getId() + ": In fila per l'operazione");
        salaOperazioneEntra.acquire();
        System.out.println(getId() + ": Entro in sala operatoria");
        salaPreparazione.release();
    }

    @Override
    void pazienteEsci() throws InterruptedException {
        salaOperazioneEsci.acquire();
        System.out.println(getId() + ": Vado via.");
    }

    public static void main(String[] args) {
        CasaDiCuraSem casa = new CasaDiCuraSem();
        casa.test(10);
    }
}
