package traccia20072021;
import java.util.*;

public class Sistema {
	
	private LinkedList<Contratto> listaContratti;
	private LinkedList<Calciatore> listaCalciatori;
	
	public Sistema(LinkedList<Contratto> listaContratti, LinkedList<Calciatore> listaCalciatori) {
		this.listaContratti = new LinkedList<>();
		this.listaContratti.addAll(listaContratti);
		this.listaCalciatori = new LinkedList<>();
		this.listaCalciatori.addAll(listaCalciatori);
	}

	public LinkedList<Contratto> getListaContratti() {
		return listaContratti;
	}

	public LinkedList<Calciatore> getListaCalciatori() {
		return listaCalciatori;
	}
	
	private Calciatore trovaCalciatore(String nome) {
		for (Calciatore c : listaCalciatori) {
			if (c.getNome().equals(nome)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean verificaDati() {
		for (Contratto c : listaContratti) {
			String squadra = c.getSquadra();
			Calciatore calciatore = trovaCalciatore(c.getCalciatore());
			if (calciatore.getSquadraAttuale().equals(squadra)) {
				return false;
			}
		}
		return true;
	}
	
	private ArrayList<String> estraiSquadre() {
		ArrayList<String> listaSquadre = new ArrayList<>();
		for (Contratto c : listaContratti) {
			String squadra = c.getSquadra();
			if (!(listaSquadre.contains(squadra))) {
				listaSquadre.add(squadra);
			}
		}
		return listaSquadre;
	}
	
	private int spesaTotale(String squadra) {
		int spesa = 0;
		for (Contratto c : listaContratti) {
			if (c.getSquadra().equals(squadra)) {
				spesa += c.getPrezzo();
			}
		}
		return spesa;
	}
	
	private int numeroAcquisti(String squadra) {
		ArrayList<String> squadreViste = new ArrayList<>();
		int numeroAcquisti = 0;
		for (Contratto c : listaContratti) {
			if (c.getSquadra().equals(squadra)) { 
				String squadraPrecedente = trovaCalciatore(c.getCalciatore()).getSquadraAttuale();
				if (!(squadreViste.contains(squadraPrecedente))) {
					numeroAcquisti += 1;
					squadreViste.add(squadraPrecedente);
				}
			}
		}
		return numeroAcquisti;
	}
	
	public LinkedList<String> squadreAttive(int pMin) {
		LinkedList<String> ret = new LinkedList<>();
		for (String squadra : estraiSquadre()) {
			if (spesaTotale(squadra) >= pMin && numeroAcquisti(squadra) >= 3) {
				ret.add(squadra);
			}
		}		
		return ret;
	}
	
	private boolean elementoMaggioreDi(LinkedList<Integer> lista, int x) {
		for (int y : lista) {
			if (y > x) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<String> calciatoriPocoPremiati(int pMax) {
		LinkedList<String> ret = new LinkedList<>();
		for (Contratto c : listaContratti) {
			if (!elementoMaggioreDi(c.getPremi(), pMax)) {
				ret.add(c.getCalciatore());
			}
		}
		return ret;
	}
}
