package esercitazione10.esercizio1.base;

public class Risposta {

    int idProdotto;
    int quantita;
    double prezzoTotale;
    int idVenditore;

    public Risposta(String response) throws NumberFormatException{
        String[] parts = response.split(",");
        String idVenditoreToParse = parts[2];
        idVenditore = Integer.parseInt(idVenditoreToParse);
        String idProdottoToParse = parts[2];
        idProdotto = Integer.parseInt(idProdottoToParse);
        String quantitaToParse = parts[2];
        quantita = Integer.parseInt(quantitaToParse);
        String prezzoTotaleToParse = parts[3];
        prezzoTotale = Double.parseDouble(prezzoTotaleToParse);
    }

    public Risposta(double prezzoTotale, int idVenditore, int quantita, int idProdotto) {
        this.prezzoTotale = prezzoTotale;
        this.idVenditore = idVenditore;
        this.quantita = quantita;
        this.idProdotto = idProdotto;
    }

    @Override
    public String toString() {
        return idVenditore + "," + idProdotto + "," + quantita + "," + prezzoTotale;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }
}
