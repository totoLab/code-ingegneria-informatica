package tracce.museo;

import java.util.concurrent.Semaphore;

public class MuseoSemB extends MuseoC {

    Semaphore entrataSA = new Semaphore(40, true);
    Semaphore entrataSD = new Semaphore(5, true);
    Semaphore mutex = new Semaphore(1);

    int voglionoEntrare = 0;
    int rimasti = 5;
    Semaphore prossimoGruppo = new Semaphore(0);

    @Override
    void visitaSA() throws InterruptedException {
        log("In fila per la sala archeologica");
        entrataSA.acquire();
        log("Visitando la sala archeologica");
    }

    @Override
    void terminaVisitaSA() throws InterruptedException {
        log("Terminata la visita alla sala archeologica");
        entrataSA.release();
    }

    @Override
    void visitaSD() throws InterruptedException {
        log("In fila per la sala della dama");
        entrataSD.acquire();
        mutex.acquire();
        voglionoEntrare++;
        if (voglionoEntrare < 5) {
            mutex.release();
            prossimoGruppo.acquire();
        } else {
            voglionoEntrare = 0;
        }
        mutex.release();

        prossimoGruppo.release(4);

        log("Visitando la sala della dama");
    }

    @Override
    void terminaVisitaSD() throws InterruptedException {
        log("Terminata la visita alla sala della dama");
        mutex.acquire();
        rimasti--;
        if (rimasti == 0) {
            entrataSD.release(5);
            rimasti = 5;
        }
        mutex.release();
    }

    public static void main(String[] args) {
        MuseoC museo = new MuseoSemB();
        museo.test(150);
    }
}
