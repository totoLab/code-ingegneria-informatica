package poo.agendina;

import java.util.*;

public class AgendinaLL extends AgendinaAbstract {

    private List<Nominativo> tabella = new LinkedList<>();

    @Override
    public void svuota() {
        tabella.clear();
    }

    @Override
    public void aggiungi(Nominativo n) {
        ListIterator<Nominativo> it = tabella.listIterator();
        boolean flag = false;
        while(it.hasNext()) {
            Nominativo nominativo = it.next();
            if (nominativo.equals(n)) {
                it.set(n);
                flag = true;
            } else if(nominativo.compareTo(n) > 0) {
                it.previous();
                it.add(n);
                flag = true;
            }
        }
        if (!flag) it.add(n);
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }
}
