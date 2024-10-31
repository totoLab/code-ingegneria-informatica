package tracce.callcenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenterLC extends CallCenter {

    Random random = new Random();

    // assegnazione di clienti e operatori
    Lock l = new ReentrantLock();
    Condition operatoreLibero = l.newCondition();
    Condition clienteDisponibile = l.newCondition();
    Condition soluzioneOperatore = l.newCondition();
    Condition testSoluzione = l.newCondition();

    LinkedList<Thread> opeatoriDisponibili = new LinkedList<>();
    LinkedList<Thread> coda = new LinkedList<>();
    HashMap<Thread, Integer> coppia = new HashMap<>();
    HashMap<Integer, Boolean> risolto = new HashMap<>();
    HashMap<Integer, Boolean> soluzioni = new HashMap<>();
    HashMap<Thread, Integer> pausa = new HashMap<>();

    boolean mioTurno() {
        return Thread.currentThread() == coda.getFirst();
    }

    boolean soluzione() {
        boolean risolto = random.nextBoolean();
        attesa(2, 6);
        return risolto;
    }

    @Override
    void richiediAssistenza() {
        l.lock();
        try {
            log("In attesa di assistenza", Tipo.CLIENTE);
            clienteDisponibile.signal();
            coda.add(Thread.currentThread());
            while (opeatoriDisponibili.isEmpty() || !mioTurno()) {
                operatoreLibero.await();
            }
            Cliente cliente = (Cliente) coda.removeFirst();
            risolto.put(cliente.id, false);

            Thread operatore = opeatoriDisponibili.remove();
            coppia.put(operatore, cliente.id);
            log("Operatore assegnato", Tipo.CLIENTE);

            // attendi la soluzione e applicala
            log("Attendo una soluzione", Tipo.CLIENTE);
            while (
                    !(soluzioni.containsKey(cliente) && soluzioni.get(cliente)
                    ) && !soluzione()
            ) {
                log("Attendo una nuova soluzione", Tipo.CLIENTE);
                testSoluzione.signalAll();
                soluzioneOperatore.await();
            }
            log("Ha funzionato!", Tipo.CLIENTE);
            risolto.put(cliente.id, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }

    @Override
    void fornisciAssistenza() {
        l.lock();
        try {
            log("Operatore disponibile", Tipo.OPERATORE);
            opeatoriDisponibili.add(Thread.currentThread());
            while (coda.isEmpty() || !coppia.containsKey(Thread.currentThread())) {
                clienteDisponibile.await();
            }

            if (!pausa.containsKey(Thread.currentThread())) {
                pausa.put(Thread.currentThread(), 0);
            }
            pausa.put(Thread.currentThread(), pausa.get(Thread.currentThread()) + 1);

            int idCliente = coppia.get(Thread.currentThread());
            while (!risolto.get(idCliente)) {
                log("Genero una soluzione", Tipo.OPERATORE);
                attesa(1, 10); // crea soluzione
                soluzioni.put(idCliente, true);
                soluzioneOperatore.signalAll();
                testSoluzione.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }

    @Override
    void terminaChiamata() {
        l.lock();
        try {
            testSoluzione.signalAll();
            log("Risolto, arrivederci", Tipo.CLIENTE);
        } finally {
            l.unlock();
        }
    }

    @Override
    void prossimoCliente() {
        if (pausa.get(Thread.currentThread()) % 15 == 0) {
            log("In pausa", Tipo.OPERATORE);
            attesa(5);
        }
        l.lock();
        try {
            operatoreLibero.signalAll();
        } finally {
            l.unlock();
        }
    }


    public static void main(String[] args) {
        CallCenter cc = new CallCenterLC();
        cc.test(1, 3);
    }
}
