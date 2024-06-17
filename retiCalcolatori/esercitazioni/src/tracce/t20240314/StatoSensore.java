package tracce.t20240314;

public class StatoSensore {

    final private int id;
    private int numeroProgressivo;
    private int temperatura;
    private int umidita;

    public StatoSensore(int id, int temperatura, int umidita) {
        this.id = id;
        this.temperatura = temperatura;
        this.umidita = umidita;
    }

    public int getId() {
        return id;
    }

    public void setNumeroProgressivo(int numeroProgressivo) {
        this.numeroProgressivo = numeroProgressivo;
    }

    public int getNumeroProgressivo() {
        return numeroProgressivo;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public int getUmidita() {
        return umidita;
    }
}
