package tracce.t20190904.parte2;

import java.io.Serializable;

public class Statistica implements Serializable {

    String codiceEsame;
    int prenotati;
    int nonEvasi;

    public Statistica(String codiceEsame, int prenotati, int nonEvasi) {
        this.codiceEsame = codiceEsame;
        this.prenotati = prenotati;
        this.nonEvasi = nonEvasi;
    }

    public String getCodiceEsame() {
        return codiceEsame;
    }

    public void setCodiceEsame(String codiceEsame) {
        this.codiceEsame = codiceEsame;
    }

    public int getPrenotati() {
        return prenotati;
    }

    public void setPrenotati(int prenotati) {
        this.prenotati = prenotati;
    }

    public int getNonEvasi() {
        return nonEvasi;
    }

    public void setNonEvasi(int nonEvasi) {
        this.nonEvasi = nonEvasi;
    }
}
