package tracce.barmod;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarLC extends Bar {

    Lock scelta = new ReentrantLock();
    Lock[] l = new ReentrantLock[NUM_FILE];
    LinkedList<Thread>[] fila =  new LinkedList[NUM_FILE];
    Condition[] prossimoCliente = new Condition[NUM_FILE];
    int[] postiOccupati = new int[NUM_FILE];

    BarLC() {
        for (int i = 0; i < NUM_FILE; i++) {
            l[i] = new ReentrantLock();
            fila[i] = new LinkedList<>();
            prossimoCliente[i] = l[i].newCondition();
            postiOccupati[i] = 0;
        }
    }

    void log(String msg, int i, boolean postiInFilaVsOccupati) {
        String finalMsg = msg + " " + (i == 0 ? "alla cassa" : "al bancone");
        String counter = postiInFilaVsOccupati ? String.valueOf(personeInFila[i]) : String.format("%d/%d", postiOccupati[i], postiMax[i]);
        String log = String.format(
                "%d: %s (%s)", getId(), finalMsg, counter
        );
        System.out.println(log);
    }

    @Override
    int scegli() {
        scelta.lock();
        int bestFila = 0;
        try {
            if (personeInFila[0] == 0) bestFila = 0;
            else if (personeInFila[1] == 0) bestFila = 1;
            else if (personeInFila[0] == personeInFila[1]) bestFila = 0;
            else {
                if (personeInFila[0] > personeInFila[1]) {
                    bestFila = 1;
                }
            }
        } finally {
            scelta.unlock();
        }
        return bestFila;
    }

    private boolean mioTurno(int i) {
        return Thread.currentThread() == fila[i].getFirst() && postiMax[i] > postiOccupati[i];
    }

    @Override
    void inizia(int i) throws InterruptedException {
        scelta.lock();
        try {
            fila[i].add(Thread.currentThread());
            personeInFila[i] += 1;
            log("In fila", i, true);
        } finally {
            scelta.unlock();
        }

        l[i].lock();
        try {
            while (!mioTurno(i)) {
                prossimoCliente[i].await();
            }
            scelta.lock();
            try {
                fila[i].removeFirst();
                personeInFila[i] -= 1;
                log("Libero la fila", i, true);
            } finally {
                scelta.unlock();
            }
            postiOccupati[i] += 1;
            log("Preso il posto", i, false);
        } finally {
            l[i].unlock();
        }
        // l'accesso rimane bloccato dai posti disponibili, l'attesa può rimanere fuori dalla sezione critica
        attesa(i);
    }

    @Override
    void finisci(int i) throws InterruptedException {
        l[i].lock();
        try {
            postiOccupati[i] -= 1;
            log("Finito", i, false);
            prossimoCliente[i].signalAll();
        } finally {
            l[i].unlock();
        }
    }

    public static void main(String[] args) {
        BarLC bar = new BarLC();
        bar.test(100);
    }
}
