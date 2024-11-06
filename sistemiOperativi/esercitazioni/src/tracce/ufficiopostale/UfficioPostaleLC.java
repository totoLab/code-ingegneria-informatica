package tracce.ufficiopostale;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UfficioPostaleLC extends UfficioPostale {

    int[] durataOperazione = new int[] { 3, 5, 7};

    Lock l = new ReentrantLock();
    Condition possoRitirare = l.newCondition();

    LinkedList<Thread> coda = new LinkedList<>();
    int[] ticketEmessi = new int[] { 0, 0, 0 };
    final int TICKET_MAX = 50;

    Map<Integer, LinkedList<Thread>> sportelli = Map.of(
            0, new LinkedList<>(),
            1, new LinkedList<>(),
            2, new LinkedList<>()
    );
    Condition turnoSportello = l.newCondition();
    boolean[] clienteAlloSportello = new boolean[] { false, false, false };
    Condition[] clienteDisponibile = new Condition[] { l.newCondition(), l.newCondition(), l.newCondition() };

    boolean[] operazioneTerminata = new boolean[] { false, false, false };
    Condition[] termineOperazione = new Condition[] { l.newCondition(), l.newCondition(), l.newCondition() };

    UfficioPostaleLC(int numeroImpiegatiPerTipo) {
        super(numeroImpiegatiPerTipo);
    }

    int converti(String operazione) {
        Map<String, Integer> op = Map.of(
                "A", 0,
                "B", 1,
                "C", 2
        );
        return op.get(operazione);
    }

    @Override
    boolean ritiraTicket(String operazione) throws InterruptedException {
        l.lock();
        try {
            coda.add(Thread.currentThread());
            while (!Thread.currentThread().equals(coda.getFirst())) {
                possoRitirare.await();
            }
            coda.removeFirst();
            int codice = converti(operazione);
            if (ticketEmessi[codice] == TICKET_MAX) return false;
            ticketEmessi[codice]++;
            possoRitirare.signalAll();
            return true;
        } finally {
            l.unlock();
        }
    }

    boolean mioTurno(int codice) {
        return Thread.currentThread().equals(sportelli.get(codice).getFirst());
    }

    @Override
    void attendiSportello(String operazione) throws InterruptedException {
        l.lock();
        try {
            int codice = converti(operazione);
            while (!mioTurno(codice)) {
                turnoSportello.await();
            }
            clienteAlloSportello[codice] = true;
            while (!operazioneTerminata[codice]) {
                termineOperazione[codice].await();
            }

        } finally {
            l.unlock();
        }
    }

    @Override
    void prossimoCliente() throws InterruptedException {
        l.lock();
        try {
            turnoSportello.signalAll();
        } finally {
            l.unlock();
        }
    }

    @Override
    void eseguiOperazione() throws InterruptedException {
        l.lock();
        int codice;
        try {
            codice = ((Impiegato) Thread.currentThread()).tipo;
            while (!clienteAlloSportello[codice]) {
                clienteDisponibile[codice].await();
            }
        } finally {
            l.unlock();
        }

        attesa(durataOperazione[codice]);

        l.lock();
        try {
            termineOperazione[codice].signal();
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        UfficioPostale ufficio = new UfficioPostaleLC(1);
        ufficio.test(200);
    }
}
