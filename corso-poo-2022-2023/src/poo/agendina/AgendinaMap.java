package poo.agendina;

import java.util.*;

public class AgendinaMap extends AgendinaAbstract {

	private Map<Nominativo, Nominativo> tabella = new TreeMap<>();
	
	@Override
	public int size() {
		return tabella.size();
	}
	
	@Override
	public void aggiungi(Nominativo n) {
		tabella.put(n, n);
	}

	@Override
	public void svuota() {
		tabella.clear();
	}

	@Override
	public void rimuovi(Nominativo n) {
		tabella.remove(n);
	}
	
	@Override
	public Nominativo cerca(Nominativo n) {
		return tabella.get(n);
	}
	
	@Override
	public Iterator<Nominativo> iterator() {
		return tabella.values().iterator();
	}

}
