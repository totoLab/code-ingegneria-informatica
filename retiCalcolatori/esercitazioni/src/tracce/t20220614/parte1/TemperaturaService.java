package tracce.t20220614.parte1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperaturaService {

    ListaCitta listaCitta;
    HashMap<String, Temps> temperatures = new HashMap<>();

    class Temps {
        double max;
        double min;
    }

    class Citta {
        String Nome;
        String Provincia;
        String Regione;
    }

    class ListaCitta {
        List<Citta> lista;
    }

    Citta MaxDiff(String in0, String in1) {
        Map<Citta, Double> differenze = new HashMap<>();
        for (Citta c : listaCitta.lista) {
            if (c.Regione.equals(in0) && c.Provincia.equals(in1)) {
                if (temperatures.containsKey(c.Nome)) {
                    Temps temps = temperatures.get(c.Nome);
                    differenze.put(c,temps.max - temps.min);
                }
            }
        }
        Double max = null;
        Citta cittaMax = null;
        for (Citta c : differenze.keySet()) {
            Double diff = differenze.get(c);
            if (max == null || diff > max) {
                cittaMax = c;
                max = diff;
            }
        }
        return cittaMax;
    }

    String Soglia(double in0, String in1) {
        List<Citta> cittaSoglia = new ArrayList<>();
        for (Citta c : listaCitta.lista) {
            for (String n : temperatures.keySet()) {
                if (c.Regione.equals(in1) && c.Nome.equals(n)) {
                    if (temperatures.get(n).max > in0) cittaSoglia.add(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Citta c : cittaSoglia) {
            sb.append(c + ",");
        }

        return sb.toString();
    }

}
