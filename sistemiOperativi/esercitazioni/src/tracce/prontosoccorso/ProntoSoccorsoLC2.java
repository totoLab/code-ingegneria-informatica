package tracce.prontosoccorso;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProntoSoccorsoLC2 extends ProntoSoccorso {

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

    Map<Thread, Integer> pazienteMedico = new HashMap<>();
    Condition[] attendiPaziente = new Condition[numeroMedici];
    Condition[] fineVisita = new Condition[numeroMedici];
    Thread[] pazienteCorrente = new Thread[numeroMedici];
    boolean[] pazienteEntrato = new boolean[numeroMedici];
    boolean[] visitaFinita = new boolean[numeroMedici];

    ProntoSoccorsoLC2(int numeroMedici) {
        super(numeroMedici);
        for (int i = 0; i < numeroMedici; i++) {
            attendiPaziente[i] = l.newCondition();
            fineVisita[i] = l.newCondition();
        }
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
            int medico = ((Medico) Thread.currentThread()).id;

            while (pazientiCodice.isEmpty()) {
                pazienteDisponibile.await();
            }
            visitaFinita[medico] = false;
            chiamataCodice.signalAll();
            while (!pazienteEntrato[medico]) {
                attendiPaziente[medico].await();
            }
            salaAttesa();

            codice = pazientiCodice.get(pazienteCorrente[medico]);
        } finally {
            l.unlock();
        }
        attesa(durataVisita[2 * codice], durataVisita[2 * codice + 1]);
    }

    @Override
    void terminaVisita() throws InterruptedException {
        l.lock();
        try {
            int medico = ((Medico) Thread.currentThread()).id;

            visitaFinita[medico] = true;
            fineVisita[medico].signal();
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

            int medico = pazienteMedico.get(paziente);
            pazienteCorrente[medico] = paziente;
            pazienteEntrato[medico] = true;
            attendiPaziente[medico].signal();
        } finally {
            l.unlock();
        }
    }

    @Override
    void esciPaziente() throws InterruptedException {
        l.lock();
        try {
            Thread paziente = Thread.currentThread();
            int medico = pazienteMedico.get(paziente);

            while (!visitaFinita[medico]) {
                fineVisita[medico].await();
            }
            pazienteEntrato[medico] = false;
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        ProntoSoccorso ps = new ProntoSoccorsoLC2(3);
        ps.test(30);
    }
}
