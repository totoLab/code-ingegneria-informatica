package tracce.aziendaagricola;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLC extends AziendaAgricola {

    LinkedList<Integer> codaCassa = new LinkedList<>();
    LinkedList<Integer> codaMagazzino = new LinkedList<>();

    Lock lockCassa = new ReentrantLock();
    Condition cassaLibera = lockCassa.newCondition();

    boolean isCassaLibera = true;

    Lock lockMagazzino = new ReentrantLock();

    Condition magazzinoLibero = lockMagazzino.newCondition();

    Condition magazzinoVuoto = lockMagazzino.newCondition();
    Condition sacchiDisponibili = lockMagazzino.newCondition();



    boolean mioTurnoCassa(int id) {
        return codaCassa.getFirst() == id;
    }

    boolean mioTurnoMagazzino(int id) {
        return codaMagazzino.getFirst() == id;
    }

    @Override
    void paga(int id, int i) {
        lockCassa.lock();
        try {
            codaCassa.add(id);
            while (!isCassaLibera || !mioTurnoCassa(id)) {
                cassaLibera.await();
            }
            isCassaLibera = false;
            codaCassa.remove();

            System.out.printf("Il cliente %d sta pagando per %d sacchi.%n", id, i);
            TimeUnit.SECONDS.sleep(15);

            isCassaLibera = true;
            cassaLibera.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lockCassa.unlock();
        }
    }

    @Override
    void prendiSacchi(int id, int n) {
        lockMagazzino.lock();
        try {
            codaMagazzino.add(id);
            while (sacchiRimasti == 0 || !mioTurnoMagazzino(id)) {
                magazzinoLibero.await();
            }
            System.out.printf("Il cliente %d è entrato in magazzino per %d sacchi.%n", id, n);
            codaMagazzino.remove();

            for (int i = 0; i < n; i++) {
                sacchiRimasti -= 1;
                System.out.printf("Il cliente %d porta via un sacco dal magazzino (rimasti %d).%n", id, sacchiRimasti);
                TimeUnit.MINUTES.sleep(10);
                if (sacchiRimasti == 0) {
                    magazzinoVuoto.signal();
                }
                while (sacchiRimasti == 0) {
                    sacchiDisponibili.await();
                }
            }

            magazzinoLibero.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lockMagazzino.unlock();
        }
    }

    @Override
    void restock() {
        lockMagazzino.lock();
        try {
            while (sacchiRimasti != 0) {
                magazzinoVuoto.await();
            }
            System.out.println("Restock in corso...");
            TimeUnit.MINUTES.sleep(10);
            sacchiRimasti += sacchiMax;
            System.out.println("Restock finito, sacchi disponibili: " + sacchiRimasti);
            sacchiDisponibili.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockMagazzino.unlock();
        }
    }

    public static void main(String[] args) {
        AziendaAgricola aziendaAgricola = new AziendaAgricolaLC();
        aziendaAgricola.test(15);
    }
}
