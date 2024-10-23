package tracce.t20230619.parte2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RispostaSondaggio implements Serializable {
    List<String> risposte;

    public List<String> getRisposte() {
        return new ArrayList<>(risposte);
    }
}
