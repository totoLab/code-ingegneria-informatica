import java.util.*;

public class Sistema {
	
	private LinkedList<Componente> componenti;
	private LinkedList<Articolo> articoli;
	
	public Sistema(LinkedList<Componente> componenti, LinkedList<Articolo> articoli) {
		this.componenti = new LinkedList<>(componenti);
		this.articoli = new LinkedList<>(articoli);
	}
	
	private Componente trovaComponente(String codice) {
		for (Componente c : componenti) {
			if (c.getCodice().equals(codice)) {
				return c;
			}
		}
		return null;
	}
	
	private float profitto(Articolo a) {
		float sommaPrezziComponenti = 0;
		for (String codice : a.getComponenti()) {
			sommaPrezziComponenti += trovaComponente(codice).getPrezzo();
		}
		return a.getPrezzo() - sommaPrezziComponenti;
	}
	
	public String articoloTop() {
		Articolo articoloMax = null;
		float profittoMax = 0;
		for (Articolo a : articoli) {
			float profittoA = profitto(a);
			if (profittoA > profittoMax) {
				profittoMax = profittoA;
				articoloMax = a;
			}
		}
		return articoloMax.getNome();
	}
	
	private boolean eUniversale(String codice) {
		for (Articolo a : articoli) {
			 if (!a.getComponenti().contains(codice)) {
				 return false;
			 }
		}
		return true;
	}
	
	public LinkedList<String> componentiUniversali() {
		LinkedList<String> ret = new LinkedList<>();
		for (Componente c : componenti) {
			String codice = c.getCodice();
			if (eUniversale(codice)) {
				ret.addLast(codice);
			}
		}
		return ret;
	}
	
	private boolean haComponentiCostosi(Articolo a, float p) {
		for (String codice : a.getComponenti()) {
			if (trovaComponente(codice).getPrezzo() > p) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<String> articoliComponentiCostosi(float p) {
		LinkedList<String> ret = new LinkedList<>();
		for (Articolo a : articoli) {
			if (haComponentiCostosi(a, p)) {
				ret.addLast(a.getNome());
			}
		}
		return ret;
	}		
}
