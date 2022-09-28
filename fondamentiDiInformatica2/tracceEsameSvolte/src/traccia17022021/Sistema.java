package traccia17022021;

import java.util.*;

public class Sistema {

	LinkedList<Componente> listaComponenti;
	LinkedList<Articolo> listaArticoli;
	
	public Sistema(LinkedList<Componente> listaComponenti, LinkedList<Articolo> listaArticoli) {
		this.listaComponenti = new LinkedList<>(listaComponenti);
		this.listaArticoli = new LinkedList<>(listaArticoli);
	}
	
	private float calcolaProfitto(Articolo a) {
		float somma = 0;
		for (String codice : a.getComponenti()) {
			somma += trovaComponente(codice).getPrezzo();
		}
		return a.getPrezzo() - somma;
	}
	
	public String articoloTop() {
		String ret = listaArticoli.get(0).getNome();
		float max = -1;
		for (Articolo a : listaArticoli) {
			float profittoA = calcolaProfitto(a);
			if (profittoA > max) {
				ret = a.getNome();
				max = profittoA;
			}
		}
		return ret;
	}
	
	private boolean universale(String codice) {
		for (Articolo a : listaArticoli) {
			if (!(a.getComponenti().contains(codice))) {
				return false;
			}
		}
		return true;
	}
	
	public LinkedList<String> componentiUniversali() {
		LinkedList<String> ret = new LinkedList<>();
		for (Componente c : listaComponenti) {
			String codice = c.getCodice();
			if (universale(codice)) {
				ret.add(codice);
			}
		}
		return ret;
	}
	
	private Componente trovaComponente(String codice) {
		for (Componente c : listaComponenti) {
			if (c.getCodice().equals(codice)) {
				return c;
			}
		}
		return null;
	}
	
	private boolean esistePiuCostosoDi(Articolo a, float p) {
		for (String codice : a.getComponenti()) {
			Componente c = trovaComponente(codice);
			if (c.getPrezzo() > p) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<String> articoliComponentiCostosi(float p) {
		LinkedList<String> ret = new LinkedList<>();
		for (Articolo a : listaArticoli) {
			if (esistePiuCostosoDi(a, p)) {
				ret.add(a.getNome());
			}
		}
		return ret;
	}
}
