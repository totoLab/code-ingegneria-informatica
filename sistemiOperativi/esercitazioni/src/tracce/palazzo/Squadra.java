package tracce.palazzo;

public class Squadra extends Thread {

    Palazzo palazzo;
    int tipo;

    Squadra(Palazzo palazzo, int tipo) {
        this.palazzo = palazzo;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        while (!palazzo.eFinito()) {
            try {
                palazzo.start(tipo);
                palazzo.end(tipo);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(tipo + ": squadra va a casa");
    }
}
