package tracce.museo;

import java.util.concurrent.Semaphore;

public class MuseoSemA extends MuseoC {

    Semaphore entrataSA = new Semaphore(40, true);
    Semaphore entrataSD = new Semaphore(5, true);

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
        log("Visitando la sala della dama");
    }

    @Override
    void terminaVisitaSD() throws InterruptedException {
        log("Terminata la visita alla sala della dama");
        entrataSD.release();
    }

    public static void main(String[] args) {
        MuseoC museo = new MuseoSemA();
        museo.test(154);
    }
}
