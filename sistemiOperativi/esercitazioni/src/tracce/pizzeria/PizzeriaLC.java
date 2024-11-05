package tracce.pizzeria;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PizzeriaLC extends Pizzeria {

    Lock l = new ReentrantLock();
    Condition postoLibero = l.newCondition();

    LinkedList<Thread> coda = new LinkedList<>();
    int postiLiberi = 5;

    Condition preparazione = l.newCondition();

    Condition pizza = l.newCondition();
    boolean pizzaPronta = false;
    boolean pizzaPrecedenteFinita = true;

    boolean mioTurno() {
        return Thread.currentThread().equals(coda.getFirst());
    }

    @Override
    void entra() throws InterruptedException {
        l.lock();
        try {
            log("In coda fuori dalla pizzeria", Tipo.CLIENTE);
            coda.add(Thread.currentThread());
            while (!(postiLiberi > 0 && mioTurno())) {
                postoLibero.await();
            }
            log("Mi siedo al tavolo", Tipo.CLIENTE);
            coda.removeFirst();
            postiLiberi--;
            if (postiLiberi == 0) {
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
            postiLiberi++;
            if (postiLiberi == 5) {
                log("Tavolo liberato", Tipo.CLIENTE);
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
            while (postiLiberi > 0 || !pizzaPrecedenteFinita) {
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
    void serviPizza() throws InterruptedException {
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
        Pizzeria pizzeria = new PizzeriaLC();
        pizzeria.test(12);
    }
}
