package tracce.t20230711.parte1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrtaggiService {

    HashMap<String, HashMap<Grossista, PrezzoProdotto>> ortaggi = new HashMap<>();
    List<Grossista> grossisti = new LinkedList<>();

    class Grossista {
        String partitaIva;
        String provincia;
    }

    class PrezzoProdotto {
        String partitaIva;
        String nomeOrtaggio;
        Double prezzo;
    }

    Grossista getGrossista(String partitaIva) {
        for (Grossista g : grossisti) {
            if (g.partitaIva.equals(partitaIva)) {
                return g;
            }
        }
        return null;
    }

    void UpdatePrezzo(PrezzoProdotto prezzoProdotto) {
        ortaggi.get(prezzoProdotto.nomeOrtaggio).put(getGrossista(prezzoProdotto.partitaIva), prezzoProdotto);
    }


    class SommaCont {
        double somma = 0;
        int cont = 0;
    }

    String MinPrezzoMedioResponse(String ortaggio) {
        HashMap<String, SommaCont> provinciaPrezzi = new HashMap<>();
        for (Grossista g : ortaggi.get(ortaggio).keySet()) {
            if (!provinciaPrezzi.containsKey(g.provincia)) {
                provinciaPrezzi.put(g.provincia, new SommaCont());
            }
            PrezzoProdotto prezzoProdotto = ortaggi.get(ortaggio).get(g);
            provinciaPrezzi.get(g.provincia).somma += prezzoProdotto.prezzo;
            provinciaPrezzi.get(g.provincia).cont += 1;
        }

        String provinciaMin = provinciaPrezzi.keySet().stream().toList().getFirst();
        SommaCont scmMin = provinciaPrezzi.get(provinciaMin);
        double mediaMin = scmMin.somma / scmMin.cont;
        for (String provincia : provinciaPrezzi.keySet()) {
            SommaCont scm = provinciaPrezzi.get(provincia);
            if (scm.cont > 0) {
                double media = scm.somma / scm.cont;
                if (media < mediaMin) {
                    mediaMin = media;
                    provinciaMin = provincia;
                }
            }
        }
        return provinciaMin;
    }
}
