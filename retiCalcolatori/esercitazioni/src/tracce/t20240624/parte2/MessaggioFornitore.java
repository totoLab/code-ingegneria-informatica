package tracce.t20240624.parte2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessaggioFornitore implements Serializable {
    final String nome;
    final List<Partita> partite;

    MessaggioFornitore(String nome, List<Partita> partite) {
        this.nome = nome;
        this.partite = new ArrayList<>(partite);
    }
}