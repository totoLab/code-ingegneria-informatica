package tracce.casadicura;

public class Medico extends Thread {

    CasaDiCura casaDiCura;

    Medico(CasaDiCura casaDiCura) {
        this.casaDiCura = casaDiCura;
    }

    @Override
    public void run() {
        try {
            while (true) {
                casaDiCura.chiamaEIniziaOperazione();
                casaDiCura.fineOperazione();
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
