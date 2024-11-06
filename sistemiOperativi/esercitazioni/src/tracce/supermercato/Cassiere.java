package tracce.supermercato;

public class Cassiere extends Thread {

    Cassa cassa;
    int id;

    Cassiere(Cassa cassa, int id) {
        this.cassa = cassa;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                cassa.scansiona();
                cassa.prossimoCliente();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
