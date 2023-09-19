package traccia28062023;

import java.util.*;

public class Sistema { //!!! equals in support classes are not overridden

	LinkedList<Pilota> listaPiloti;
	LinkedList<Scuderia> listaScuderie;
	LinkedList<Gara> listaGare;
	
	public Sistema(LinkedList<Pilota> listaPiloti, LinkedList<Scuderia> listaScuderie, LinkedList<Gara> listaGare) {
		super();
		this.listaPiloti.addAll(listaPiloti);
		this.listaScuderie.addAll(listaScuderie);
		this.listaGare.addAll(listaGare);
	}
	
	private Pilota estraiPilota(String nome) {
		for (Pilota p : listaPiloti) {
			if (p.getNome().equals(nome)) {
				return p;
			}
		}
		return null;
	}
	
	private Scuderia estraiScuderia(String nome) {
		for (Scuderia s : listaScuderie) {
			if (s.getNome().equals(nome)) {
				return s;
			}
		}
		return null;
	}
	
	private int numeroPilotiDellaScuderia(LinkedList<String> nomiPiloti, LinkedList<String> nomiScuderie, String citta) {
		ArrayList<Pilota> pilotiDiversi = new ArrayList<>();
		ListIterator<String> itScuderie = nomiScuderie.listIterator();
		ListIterator<String> itPiloti = nomiPiloti.listIterator();
		while (itScuderie.hasNext() && itPiloti.hasNext()) {
			String nomeScuderia = itScuderie.next();
			String nomePilota = itPiloti.next();
			
			Scuderia s = estraiScuderia(nomeScuderia);
			if (s.getCitta().equals(citta)) {
				Pilota p = estraiPilota(nomePilota);
				if (!pilotiDiversi.contains(p)) {
					pilotiDiversi.add(p);
				}
			}
		}
		return pilotiDiversi.size();
	}
	
	public int numeroPiloti(String c1, String c2) {
		int counter = 0;
		for (Gara g : listaGare) {
			String cittaGara = g.getLuogo();
			if (cittaGara.equals(c1)) {
				counter += numeroPilotiDellaScuderia(g.getPiloti(), g.getScuderie(), c2);
			}
		}
		return counter;
	}
	
	public float compensoTotaleScuderia(String s) {
		Scuderia sc = estraiScuderia(s);
		float compensoTotale = 0;
		for (Gara g : listaGare) {
			ListIterator<String> itScuderieGara = g.getScuderie().listIterator();
			ListIterator<String> itPilotiGara = g.getPiloti().listIterator();
			boolean primoPilota = true;
			while (itScuderieGara.hasNext() && itPilotiGara.hasNext()) {
				if (itScuderieGara.next().equals(s)) {
					Pilota p = estraiPilota(itPilotiGara.next());
					compensoTotale += p.getCompensoGara();
					if (primoPilota) {
						compensoTotale += sc.getCompensoExtra();
						primoPilota = false;
					}
				}
				
			}
		}
		return compensoTotale;
	}
	
}
