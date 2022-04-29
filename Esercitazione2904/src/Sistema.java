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
			if (c.getCodice.equals(codice)) {
				return c;
			}
		}
		return null;
	}
	
	private float profitto(Articolo a) {
		float sommaPrezziComponenti = 0;
		for (String codice : a.getComponenti()) {
			sommaPrezziComponenti += trovaComponente(codice).getPrezzo;
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
	
}
