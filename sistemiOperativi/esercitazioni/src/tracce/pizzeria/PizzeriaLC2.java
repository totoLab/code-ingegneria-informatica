package tracce.pizzeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PizzeriaLC2 extends Pizzeria {

    final int numeroTavoli;
    final int POSTI_TAVOLO = 5;

    int tavoloDaOccupare = 0;

    Lock l = new ReentrantLock();
    Condition postoLibero = l.newCondition();

    LinkedList<Thread> coda = new LinkedList<>();
    int[] postiLiberi;
    Map<Thread, Integer> clienti = new HashMap<>();

    Condition preparazione = l.newCondition();

    Condition pizza = l.newCondition();
    boolean pizzaPronta = false;
    boolean pizzaPrecedenteFinita = true;

    public PizzeriaLC2(int numeroTavoli) {
        this.numeroTavoli = numeroTavoli;
        postiLiberi = new int[numeroTavoli];
        for (int i = 0; i < numeroTavoli; i++) {
            postiLiberi[i] = POSTI_TAVOLO;
        }
    }

    boolean mioTurno() {
        return Thread.currentThread().equals(coda.getFirst());
    }

    @Override
    void entra() throws InterruptedException {
        l.lock();
        try {
            log("In coda fuori dalla pizzeria", Tipo.CLIENTE);
            coda.add(Thread.currentThread());
            while (!(postiLiberi[tavoloDaOccupare] > 0 && mioTurno())) {
                postoLibero.await();
            }
            log("Mi siedo al tavolo", Tipo.CLIENTE);
            Thread cliente = coda.removeFirst();
            clienti.put(cliente, tavoloDaOccupare);
            postiLiberi[tavoloDaOccupare]--;
            if (postiLiberi[tavoloDaOccupare] == 0) {
                tavoloDaOccupare = (tavoloDaOccupare + 1) % numeroTavoli;
                preparazione.signal();
                log("Siamo tutti, prepara la pizza", Tipo.CLIENTE);
            }

        } finally {
            l.unlock();
        }
    }

    @Override
    void mangiaPizza() throws InterruptedException {
        l.lock();
        try {
            log("Aspetto la pizza", Tipo.CLIENTE);
            while (!pizzaPronta) {
                pizza.await();
            }
            log("Buon appetito!", Tipo.CLIENTE);
        } finally {
            l.unlock();
        }

        attesa(5, 10);

        l.lock();
        try {
            log("Finito, arrivederci", Tipo.CLIENTE);
            int tavolo = clienti.get(Thread.currentThread());
            postiLiberi[tavolo]++;
            if (postiLiberi[tavolo] == 5) {
                log("Tavolo liberato", Tipo.CLIENTE);
                tavoloDaOccupare = tavolo;
                pizzaPronta = false;
                pizzaPrecedenteFinita = true;
                postoLibero.signalAll();
            }
        } finally {
            l.unlock();
        }
    }

    @Override
    void preparaPizza() throws InterruptedException {
        l.lock();
        try {
            log("Attendo i clienti", Tipo.PIZZAIOLO);
            while (postiLiberi[tavoloDaOccupare] > 0 || !pizzaPrecedenteFinita) {
                preparazione.await();
            }
            pizzaPrecedenteFinita = false;
        } finally {
            l.unlock();
        }
        log("Preparo la pizza", Tipo.PIZZAIOLO);
        attesa(10);
    }

    @Override
    void serviPizza() {
        l.lock();
        try {
            pizzaPronta = true;
            log("Pizza servita, buon appetito", Tipo.PIZZAIOLO);
            pizza.signalAll();
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Pizzeria pizzeria = new PizzeriaLC2(2);
        pizzeria.test(10);
    }
}
