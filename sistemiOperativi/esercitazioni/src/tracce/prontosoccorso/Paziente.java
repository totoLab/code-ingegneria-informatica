package tracce.prontosoccorso;

public class Paziente extends Thread {

    ProntoSoccorso ps;

    Paziente(ProntoSoccorso ps) {
        this.ps = ps;
    }

    @Override
    public void run() {
        try {
            ps.accediPaziente();
            ps.esciPaziente();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
