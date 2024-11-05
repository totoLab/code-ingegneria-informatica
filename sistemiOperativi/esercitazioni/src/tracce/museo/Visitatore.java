package tracce.museo;

public class Visitatore extends Thread {

    MuseoC museo;

    Visitatore(MuseoC museo) {
        this.museo = museo;
    }

    @Override
    public void run() {
        try {
            museo.visitaSA();
            museo.attesa(20, 40);
            museo.terminaVisitaSA();
            museo.visitaSD();
            museo.attesa(5, 8);
            museo.terminaVisitaSD();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
