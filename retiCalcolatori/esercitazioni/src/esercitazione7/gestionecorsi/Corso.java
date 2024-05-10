package esercitazione7.gestionecorsi;

import java.io.Serializable;

public class Corso implements Serializable {

    private String programma, docente, nomeCorso;
    private int numeroCrediti, oreLezione, oreEsercitazione;

    public String getProgramma() {
        return programma;
    }

    public String getDocente() {
        return docente;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public int getCrediti() {
        return numeroCrediti;
    }

    public int getOreLezione() {
        return oreLezione;
    }

    public int getOreEsercitazione() {
        return oreEsercitazione;
    }
}
