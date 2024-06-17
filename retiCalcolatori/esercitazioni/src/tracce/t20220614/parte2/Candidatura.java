package tracce.t20220614.parte2;

import java.io.Serializable;

public class Candidatura implements Serializable {

    final int id;
    final String url;

    public Candidatura(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }
}
