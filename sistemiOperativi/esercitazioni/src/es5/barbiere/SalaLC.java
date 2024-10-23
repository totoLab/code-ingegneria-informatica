package es5.barbiere;

import java.util.concurrent.locks.*;

public class SalaLC extends Sala {

    Lock lock = new ReentrantLock();
    Condition clienteDisponibile = lock.newCondition();
    Condition barbiereLibero = lock.newCondition();
    boolean poltronaLibera = false;

    public SalaLC(int sedie) {
        super(sedie);
    }

    private int clientiInAttesa() {
        return numSedie - sedieLibere;
    }

    @Override
    public void tagliaCapelli() throws InterruptedException {
        lock.lock();
        try {
            while (clientiInAttesa() == 0) {
                clienteDisponibile.await();
            }
            poltronaLibera = true;
            barbiereLibero.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean attendiTaglio() throws InterruptedException {
        lock.lock();
        try {
            if (sedieLibere == 0) {
                return false;
            }
            sedieLibere--;
            clienteDisponibile.signal();
            while (!poltronaLibera) {
                barbiereLibero.await();
            }
            poltronaLibera = false;
            sedieLibere++;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SalaLC sala = new SalaLC(10);
        try {
            sala.test(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
