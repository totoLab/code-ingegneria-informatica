package tracce.t20220712.parte2;

import java.io.Serializable;

public class Richiesta implements Serializable {

    int id;
    int pezzi;

    Richiesta(int id, int pezzi) {
        this.id = id;
        this.pezzi = pezzi;
    }
}
