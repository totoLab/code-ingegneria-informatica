package tracce.funivia;

public class Turista extends Thread {

    private Funivia funivia;
    private int tipo;

    public Turista(Funivia funivia, int tipo) {
        this.funivia = funivia;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            funivia.turistaSali(tipo);
            funivia.turistaScendi(tipo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
