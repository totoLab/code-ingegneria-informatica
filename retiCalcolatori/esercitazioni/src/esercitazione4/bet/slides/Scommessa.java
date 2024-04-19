package esercitazione4.bet.slides;

import java.net.InetAddress;

public class Scommessa {
    private int ID_scommessa;
    private int N_cavallo;
    private long puntata;
    private InetAddress scommettitore;
    private static int nextID = 0;

    public Scommessa(int n_cavallo, long punt, InetAddress scommett) {
        ID_scommessa = nextID++;
        N_cavallo = n_cavallo;
        puntata = punt;
        scommettitore = scommett;
    }

    public int getID() {
        return ID_scommessa;
    }

    public int getCavallo() {
        return N_cavallo;
    }

    public long getPuntata() {
        return puntata;
    }

    public InetAddress getScommettitore() {
        return scommettitore;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Scommessa))
            return false;
        Scommessa s = (Scommessa) o;
        return N_cavallo == s.N_cavallo;
    }
}

