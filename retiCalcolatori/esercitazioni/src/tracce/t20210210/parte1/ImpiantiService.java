package tracce.t20210210.parte1;

import java.util.HashMap;
import java.util.Map;

public class ImpiantiService {

    Map<Integer, Map<String, Integer>> impiantiProdottiQuantita = new HashMap<>();
    Map<String, Integer> prodottoEnergia = new HashMap<>();

    class Risultato {
        int idImpianto;
        int quantita;

        Risultato(int idImpianto, int quantita) {
            this.idImpianto = idImpianto;
            this.quantita = quantita;
        }
    }

    void Aggiorna(int idImpianto, String idProdotto, int energia) {
        Map<String, Integer> prodottoImpianto = impiantiProdottiQuantita.get(idImpianto);
        prodottoImpianto.put(idProdotto, prodottoImpianto.get(idProdotto) + 1);
        prodottoEnergia.put(idProdotto, energia);
    }

    Risultato MaggioreEnergia(String idProdotto) {
        int energiaMax = 0;
        int impiantoMax = 0;
        for (Integer idImpianto : impiantiProdottiQuantita.keySet()) {
            int consumo = impiantiProdottiQuantita.get(idImpianto).get(idProdotto) *
                    prodottoEnergia.get(idProdotto);
            if (consumo > energiaMax) {
                energiaMax = consumo;
                impiantoMax = idImpianto;
            }
        }
        return new Risultato(impiantoMax, impiantiProdottiQuantita.get(impiantoMax).get(idProdotto));
    }
}
