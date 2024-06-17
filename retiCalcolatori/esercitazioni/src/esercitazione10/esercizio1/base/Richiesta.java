package esercitazione10.esercizio1.base;

import java.io.Serializable;

public class Richiesta implements Serializable {

    private int idProdotto;
    private int quantita;

    public Richiesta(int idProdotto, int quantita) {
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

}
