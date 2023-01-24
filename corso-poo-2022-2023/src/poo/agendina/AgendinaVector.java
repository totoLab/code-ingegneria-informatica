package poo.agendina;

import poo.util.*;
import java.util.*;
import poo.util.Vector;

public class AgendinaVector extends AgendinaAbstract {
    
    private Vector<Nominativo> v = new ArrayVector<>(100);

    @Override
    public int size() {
        return v.size();
    }

    @Override
    public void svuota() {
        v.clear();
    }

    @Override
    public void rimuovi(Nominativo n) {
        int i = Array.ricercaBinaria(v, n); // TODO implement ricercaBinaria(Vector, Nominativo) in Array
        if (i != -1) v.remove(i);
    }

    @Override
    public void aggiungi(Nominativo n) {
        int i = 0;
        while (i < size() && v.get(i).compareTo(n) < 0) {
            i++;
        }
        if (i < size() && v.get(i).equals(n)) {
            v.set(i, n);
        }
        v.add(i, n);
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return v.iterator();
    }

}
