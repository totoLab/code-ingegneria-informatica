package tracce.casadicura;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaDiCuraLC extends CasaDiCura {

    Lock l = new ReentrantLock();
    Condition operazioneInCorso = l.newCondition();
    boolean isOperazioneInCorso = false;
    Condition preparazioneSalaOperatoria = l.newCondition();
    Condition pazienteDisponibile = l.newCondition();

    Lock lockSalaAttesa = new ReentrantLock();
    Condition postiSalaPreparazioneDisponibili = lockSalaAttesa.newCondition();
    LinkedList<Thread> codaSalaOperatoria = new LinkedList<>();
    int numPostiSalaPreparazioneDisponibili = MAX_SALA_PREPARAZIONE;

    @Override
    void chiamaEIniziaOperazione() throws InterruptedException {
        l.lock();
        try {
            System.out.println("Il prossimo!");
            while (codaSalaOperatoria.isEmpty()) {
                pazienteDisponibile.await();
            }

            isOperazioneInCorso = true;
            System.out.println("Comincia l'operazione...");
            attesa(20, 40);
        } finally {
            l.unlock();
        }
    }

    @Override
    void fineOperazione() throws InterruptedException {
        l.lock();
        try {
            System.out.println("Finisce l'operazione.");
            isOperazioneInCorso = false;
            operazioneInCorso.signal();
            System.out.println("Preparazione sala operatoria...");
            attesa(20);
            System.out.println("Fine preparazione.");
            preparazioneSalaOperatoria.signalAll();
        } finally {
            l.unlock();
        }
    }

    boolean mioTurno() {
        return !isOperazioneInCorso && Thread.currentThread().equals(codaSalaOperatoria.getFirst());
    }

    @Override
    void pazienteEntra() throws InterruptedException {
        lockSalaAttesa.lock();
        try {
            System.out.println(getId() + ": In fila per la sala preparazione");
            while (numPostiSalaPreparazioneDisponibili == 0) {
                postiSalaPreparazioneDisponibili.await();
            }
            numPostiSalaPreparazioneDisponibili--;
        } finally {
            lockSalaAttesa.unlock();
        }

        l.lock();
        try {
            System.out.println(getId() + ": In fila per l'operazione");
            codaSalaOperatoria.add(Thread.currentThread());
            pazienteDisponibile.signal();
            while (!mioTurno()) {
                preparazioneSalaOperatoria.await();
            }

            System.out.println(getId() + ": Entro in sala operatoria");
            lockSalaAttesa.lock();
            try {
                numPostiSalaPreparazioneDisponibili++;
                postiSalaPreparazioneDisponibili.signalAll();
            } finally {
                lockSalaAttesa.unlock();
            }

            codaSalaOperatoria.remove();
        } finally {
            l.unlock();
        }
    }

    @Override
    void pazienteEsci() throws InterruptedException {
        l.lock();
        try {
            while (isOperazioneInCorso) {
                operazioneInCorso.await();
            }
            System.out.println(getId() + ": Vado via.");
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        CasaDiCuraLC c = new CasaDiCuraLC();
        c.test(10);
    }
}
