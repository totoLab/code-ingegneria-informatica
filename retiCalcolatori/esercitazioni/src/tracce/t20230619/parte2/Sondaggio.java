package tracce.t20230619.parte2;

import java.io.Serializable;
import java.util.*;

public class Sondaggio implements Serializable {

    Integer id;
    final String nome;
    final List<String> domande;

    public Sondaggio(String nome, List<String> domande) {
        this.nome = nome;
        this.domande = new LinkedList<>(domande);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getDomande() {
        return new LinkedList<>(domande);
    }

}
