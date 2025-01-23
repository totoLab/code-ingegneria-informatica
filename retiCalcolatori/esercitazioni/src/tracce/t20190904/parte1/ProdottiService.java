package tracce.t20190904.parte1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdottiService {

    // id -> (prodotto -> venduti)
    Map<String, Map<Prodotto, Integer>> magazziniProdottiVenduti = new HashMap<>();

    class Prodotto {
        String codice;
        String nome;
        String produttore;
        double prezzo;
    }

    class ListaProdotti {
        List<Prodotto> prodotti = new ArrayList<>();
    }

    Prodotto prodottoPiuVenduto(String in0) {
        int max = -1;
        Prodotto pmax = null;
        for (String magazzino : magazziniProdottiVenduti.keySet()) {
            Map<Prodotto, Integer> prodottiVenduti = magazziniProdottiVenduti.get(magazzino);
            for (Prodotto prodotto : prodottiVenduti.keySet()) {
                if (
                        prodotto.produttore.equals(in0) &&
                        max < prodottiVenduti.get(prodotto)
                ) {
                    max = prodottiVenduti.get(prodotto);
                    pmax = prodotto;
                }
            }
        }
        return pmax;
    }

    ListaProdotti ProdottiMaxIncasso(String in0) {
        ListaProdotti ret = new ListaProdotti();
        if (!magazziniProdottiVenduti.containsKey(in0)) {
            return ret;
        }
        Map<Prodotto, Integer> prodottiVenduti = magazziniProdottiVenduti.get(in0);
        ret.prodotti = prodottiVenduti.keySet().stream()
                .sorted((o1, o2) -> {
                    double i1 = prodottiVenduti.get(o1) * o1.prezzo;
                    double i2 = prodottiVenduti.get(o2) * o2.prezzo;
                    return Double.compare(i1, i2);
                })
                .limit(3)
                .toList();
        return ret;
    }
}
