package esercitazione7.gareappalto.slides;

import java.io.Serializable;

public class Offerta implements Serializable {
    private int id;
    private int importoRichiesto;
    private int idPartecipante;

    public Offerta(int id, int importoRichiesto, int idPartecipante) {
        this.id = id;
        this.importoRichiesto = importoRichiesto;
        this.idPartecipante = idPartecipante;
    }
    public int getId() { return id; }
    public int getImportoRichiesto() {
        return importoRichiesto;
    }
    public int getIdPartecipante() { return idPartecipante; }
    @Override
    public String toString() {
        return "Offerta{" +
                "id=" + id +
                ", importoRichiesto=" + importoRichiesto +
                '}';
    }
}