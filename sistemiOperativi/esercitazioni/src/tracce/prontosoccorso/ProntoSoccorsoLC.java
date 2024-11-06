package tracce.prontosoccorso;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProntoSoccorsoLC extends ProntoSoccorso {

    Random random = new Random();
    final int[] durataVisita = new int[]{
            10, 15,
            15, 20,
            20, 40
    };

    Map<Integer, LinkedList<Thread>> code = Map.of(
        0, new LinkedList<>(),
        1, new LinkedList<>(),
        2, new LinkedList<>()
    );
    Map<Thread, Integer> pazientiAttesa = new HashMap<>();
    Map<Thread, Integer> pazientiCodice = new HashMap<>();

    Lock l = new ReentrantLock();
    Condition chiamataCodice = l.newCondition();
    Condition pazienteDisponibile = l.newCondition();

    Condition attendiPaziente = l.newCondition();
    boolean pazienteEntrato = false;
    Thread pazienteCorrente = null;
    boolean visitaFinita = false;
    Condition fineVisita = l.newCondition();

    ProntoSoccorsoLC(int numeroMedici) {
        super(numeroMedici);
    }

    void assegnaCodice() {
        int codice = random.nextInt(3);
        LinkedList<Thread> coda = code.get(codice);
        coda.add(Thread.currentThread());
        pazientiCodice.put(Thread.currentThread(), codice);
        pazientiAttesa.put(Thread.currentThread(), 0);
    }

    void salaAttesa() {
        for (Thread t : pazientiAttesa.keySet()) {
            pazientiAttesa.put(t, pazientiAttesa.get(t) + 1);
        }
    }

    boolean mioTurno() {
        int codice = pazientiCodice.get(Thread.currentThread());
        Thread first = code.get(codice).getFirst();
        if (!Thread.currentThread().equals(first)) return false;
        if (codice == 2) {
            Thread gialloFirst = code.get(1).getFirst();
            if (pazientiAttesa.get(gialloFirst) >= 5) return false;
            return true;
        }
        if (codice == 1) {
            return code.get(0).isEmpty();
        }
        return code.get(0).isEmpty() && code.get(1).isEmpty();
    }

    @Override
    void iniziaVisita() throws InterruptedException {
        l.lock();
        int codice;
        try {
            while (pazientiCodice.isEmpty()) {
                pazienteDisponibile.await();
            }
            visitaFinita = false;
            chiamataCodice.signalAll();
            while (!pazienteEntrato) {
                attendiPaziente.await();
            }
            salaAttesa();

            codice = pazientiCodice.get(pazienteCorrente);
        } finally {
            l.unlock();
        }
        attesa(durataVisita[2 * codice], durataVisita[2 * codice + 1]);
    }

    @Override
    void terminaVisita() throws InterruptedException {
        l.lock();
        try {
            visitaFinita = true;
            fineVisita.signal();
        } finally {
            l.unlock();
        }
    }

    @Override
    void accediPaziente() throws InterruptedException {
        l.lock();
        try {
            assegnaCodice();
            while (!mioTurno()) {
                chiamataCodice.await();
            }
            Thread paziente = Thread.currentThread();
            pazientiCodice.remove(paziente);
            pazientiAttesa.remove(paziente);
            pazienteCorrente = paziente;
            pazienteEntrato = true;
            attendiPaziente.signal();
        } finally {
            l.unlock();
        }
    }

    @Override
    void esciPaziente() throws InterruptedException {
        l.lock();
        try {
            while (!visitaFinita) {
                fineVisita.await();
            }
            pazienteEntrato = false;
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        ProntoSoccorso ps = new ProntoSoccorsoLC(1);
        ps.test(30);
    }
}
