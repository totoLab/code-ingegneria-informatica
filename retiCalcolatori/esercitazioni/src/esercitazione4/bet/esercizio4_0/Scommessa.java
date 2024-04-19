package esercitazione4.bet.esercizio4_0;

public class Scommessa {

    private int cavallo;
    private double puntata;

    Scommessa(int cavallo, double puntata) {
        if (cavallo > 0 && cavallo <= 12 && puntata > 0) {
            this.cavallo = cavallo;
            this.puntata = puntata;
        } else {
            throw new IllegalArgumentException("Cavallo tra 1 e 12, puntata maggiore di 0€");
        }
    }

    public int getCavallo() {
        return cavallo;
    }

    public double getPuntata() {
        return puntata;
    }
}
