package tracce.t20230711;

public class Offerta {

    final String piva;
    final String nazione;
    final String codice;
    final double prezzo;
    final int quantita;

    public Offerta(String piva, String nazione, String codice, double prezzo, int quantita) {
        this.piva = piva;
        this.nazione = nazione;
        this.codice = codice;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }
}
