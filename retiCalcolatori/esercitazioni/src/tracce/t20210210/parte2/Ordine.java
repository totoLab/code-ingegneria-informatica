package tracce.t20210210.parte2;

import java.io.Serializable;

public class Ordine implements Serializable {

    int id;
    int quantita;
    int idAcquirente;

    public Ordine(int id, int quantita, int idAcquirente) {
        this.id = id;
        this.quantita = quantita;
        this.idAcquirente = idAcquirente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdAcquirente() {
        return idAcquirente;
    }

    public void setIdAcquirente(int idAcquirente) {
        this.idAcquirente = idAcquirente;
    }
}
