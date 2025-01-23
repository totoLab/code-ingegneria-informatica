package tracce.t20240207.parte1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SilosService {

    List<Silos> silos = new ArrayList<>();

    class Silos {
        int codice;
        String cereale;
        int quantita;
        String regione;
    }

    class ListSilos {
        List<Silos> silos = new ArrayList<>();
    }

    ListSilos quantitaCereale(String in0, int in1) {
        ListSilos li = new ListSilos();
        for (Silos silo : silos) {
            if (silo.cereale.equals(in0) && silo.quantita > in1) {
                li.silos.add(silo);
            }
        }
        return li;
    }

    ListSilos maxScorte(String in0) {
        Map<String, Silos> map = new HashMap<>();
        for (Silos silo : silos) {
            if (silo.regione.equals(in0)) {
                if (!map.containsKey(silo.cereale)) {
                    map.put(silo.cereale, silo);
                } else if (map.get(silo.cereale).quantita < silo.quantita) {
                    map.put(silo.cereale, silo);
                }
            }
        }
        ListSilos li = new ListSilos();
        li.silos = map.values().stream().toList();
        return li;
    }
}
