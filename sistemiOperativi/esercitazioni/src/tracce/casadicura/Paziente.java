package tracce.casadicura;

public class Paziente extends Thread {

    CasaDiCura casaDiCura;

    Paziente(CasaDiCura casaDiCura) {
        this.casaDiCura = casaDiCura;
    }

    @Override
    public void run() {
        try {
            casaDiCura.pazienteEntra();
            casaDiCura.pazienteEsci();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
