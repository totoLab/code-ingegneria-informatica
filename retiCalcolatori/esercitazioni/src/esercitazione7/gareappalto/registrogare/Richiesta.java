package esercitazione7.gareappalto.registrogare;

import java.io.Serializable;

public class Richiesta implements Serializable {
    private int id;
    private String descrizione;
    private int importoMassimo;
    public Richiesta(int id, String descrizione, int importoMassimo) {
        this.id = id;
        this.descrizione = descrizione;
        this.importoMassimo = importoMassimo;
    }
    public String getDescrizione() { return descrizione; }
    public int getImportoMassimo() {
        return importoMassimo;
    }

    @Override
    public String toString() {
        return "Richiesta(" + id + "){" +
                "descrizione='" + descrizione + '\'' +
                ", importoMassimo=" + importoMassimo +
                '}';
    }
}