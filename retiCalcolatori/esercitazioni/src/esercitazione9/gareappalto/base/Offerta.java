package esercitazione9.gareappalto.base;

import java.io.Serializable;

public class Offerta implements Serializable {
    private int id;
    private int importoRichiesto;
    public Offerta(int id, int importoRichiesto) {
        this.id = id;
        this.importoRichiesto = importoRichiesto;
    }
    public int getId() { return id; }
    public int getImportoRichiesto() {
        return importoRichiesto;
    }

    @Override
    public String toString() {
        return "Offerta{" +
                "id=" + id +
                ", importoRichiesto=" + importoRichiesto +
                '}';
    }
}