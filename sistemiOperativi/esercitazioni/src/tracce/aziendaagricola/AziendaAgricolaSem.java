package tracce.aziendaagricola;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AziendaAgricolaSem extends AziendaAgricola {

    Semaphore cassa = new Semaphore(1, true);
    Semaphore magazzino = new Semaphore(1, true);
    Semaphore sacchi = new Semaphore(1);
    Semaphore magazziniere = new Semaphore(0);

    @Override
    void paga(int id, int i) {
        try {
            cassa.acquire();
            System.out.printf("Il cliente %d sta pagando per %d sacchi.%n", id, i);
            TimeUnit.SECONDS.sleep(15);
            cassa.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void prendiSacchi(int id, int n) {
        try {
            magazzino.acquire();
            System.out.printf("Il cliente %d è entrato in magazzino per %d sacchi.%n", id, n);
            for (int i = 0; i < n; i++) {
                sacchi.acquire();
                sacchiRimasti -= 1;
                System.out.printf("Il cliente %d porta via un sacco dal magazzino (rimasti %d).%n", id, sacchiRimasti);
                sacchi.release();
                TimeUnit.MINUTES.sleep(10);
                if (sacchiRimasti == 0) {
                    magazziniere.release();
                }
            }
            System.out.printf("Il cliente %d va via.%n", id);
            magazzino.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    void restock() {
        try {
            magazziniere.acquire();
            sacchi.acquire();
            System.out.println("Restock in corso...");
            TimeUnit.MINUTES.sleep(10);
            sacchiRimasti += sacchiMax;
            System.out.println("Restock finito, sacchi disponibili: " + sacchiRimasti);
            sacchi.release();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        AziendaAgricola azienda = new AziendaAgricolaSem();
        azienda.test(15);
    }

}
