package esercitazione7.gestionecorsi;

import java.util.HashMap;

public class GestioneCorsi {

    HashMap<String, Corso> corsi = new HashMap<>();

    void aggiungiCorso(Corso in0) {
        corsi.put(in0.getNomeCorso(), in0);
    }

    Corso getCorso(String in0) {
        if (corsi.containsKey(in0)) {
            return corsi.get(in0);
        }
        return null;
    }
}
