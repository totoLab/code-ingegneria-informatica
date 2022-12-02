package poo.agendina;

import java.util.*;

public class AgendinaSet extends AgendinaAbstract {

	private Set<Nominativo> tabella = new TreeSet<>();
	
	@Override
	public int size() {
		return tabella.size();
	}
	
	@Override
	public void svuota() {
		tabella.clear();
	}
	
	@Override
	public void aggiungi(Nominativo n) {
		tabella.remove(n);
		tabella.add(n);
	}

	@Override
	public void rimuovi(Nominativo n) {
		tabella.remove(n);
	}
	
	@Override
	public Nominativo cerca(Nominativo n) {
		if (tabella.contains(n)) {
			Iterator<Nominativo> it = tabella.iterator();
			for (; it.hasNext(); ) {
				Nominativo nominativo = it.next();
				if (nominativo.equals(n)) return nominativo;
			}
		}
		return null;
	}
	
	@Override
	public Iterator<Nominativo> iterator() {
		return tabella.iterator();
	}

}
