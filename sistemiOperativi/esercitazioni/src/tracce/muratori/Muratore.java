package tracce.muratori;

public class Muratore extends Thread {

    Casa casa;
    int tipo;

    public Muratore(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        casa.log(tipo, "appena arrivato.");
        try {
            boolean continua = true;
            while (continua) {
                continua = casa.inizia(tipo);
                if (continua) {
                    casa.log(tipo, "inizio posa");
                    casa.attesa(10); // posa
                    casa.log(tipo, "fine posa");
                    casa.termina(tipo);
                    casa.attesa(5);
                }
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
        casa.log(tipo, "finito, vado via.");
    }
}
