package tracce.t20220614.parte2;

import java.io.Serializable;

public class Offerta implements Serializable {

    public enum Settore { S1, S2, S3 }
    public enum Tipo { IndeterminatoFulltime, IndeterminatoParttime, DeterminatoFulltime, DeterminatoParttime }

    final Settore settore;
    final String ruolo;
    final Tipo tipo;
    final double retribuzione;
    final String piva;

    public Offerta(Settore settore, String ruolo, Tipo tipo, double retribuzione, String piva) {
        this.settore = settore;
        this.ruolo = ruolo;
        this.tipo = tipo;
        this.retribuzione = retribuzione;
        this.piva = piva;
    }

    public Settore getSettore() {
        return settore;
    }

    public String getRuolo() {
        return ruolo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getRetribuzione() {
        return retribuzione;
    }

    public String getPiva() {
        return piva;
    }
}
