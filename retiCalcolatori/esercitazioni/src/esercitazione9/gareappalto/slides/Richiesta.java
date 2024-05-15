package esercitazione9.gareappalto.slides;

import java.io.Serializable;

public class Richiesta implements Serializable {
    private int idEnte; // ← Aggiungiamo questo campo per distinguere tra i vari enti
    private String descrizione;
    private int importoMassimo;

    public Richiesta(int idEnte, String descrizione, int importoMassimo) {
        this.idEnte = idEnte;
        this.descrizione = descrizione;
        this.importoMassimo = importoMassimo;
    }

    public int getIdEnte() {
        return idEnte;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getImportoMassimo() {
        return importoMassimo;
    }

    @Override
    public String toString() {
        return "Richiesta [" +
                "idEnte=" + idEnte +
                ", descrizione='" + descrizione + '\'' +
                ", importoMassimo=" + importoMassimo +
                ']';
    }
}