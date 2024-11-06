package tracce.prontosoccorso;

public class Medico extends Thread {

    ProntoSoccorso ps;
    final int id;

    Medico(ProntoSoccorso ps, int id) {
        this.ps = ps;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ps.iniziaVisita();
                ps.terminaVisita();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
