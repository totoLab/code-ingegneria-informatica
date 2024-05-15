package esercitazione9.gareappalto.registrogare;

import java.io.Serializable;

public class Offerta implements Serializable {
    private int idGara;
    private int idPartecipante;
    private int importoRichiesto;
    public Offerta(int idGara, int importoRichiesto, int idPartecipante) {
        this.idGara = idGara;
        this.importoRichiesto = importoRichiesto;
        this.idPartecipante = idPartecipante;
    }

    public int getIdGara() {
        return idGara;
    }

    public int getImportoRichiesto() {
        return importoRichiesto;
    }

    public int getIdPartecipante() {
        return idPartecipante;
    }

    @Override
    public String toString() {
        return "Offerta{" +
                "gara=" + idGara +
                ", importoRichiesto=" + importoRichiesto +
                ", partecipante=" + idPartecipante +
                '}';
    }
}