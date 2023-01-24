package traccia15022022;

import java.util.*;

public class Sistema {
	
	LinkedList<Fattura> fatture;
	LinkedList<Prodotto> prodotti;
	
	public float maxCostoFattura(int d) {
		float max = 0;
		for (Fattura f : fatture) {
			if (f.getData() == d) {
				float fMax = calcolaCostoFattura(f);
				if (fMax > max) {
					max = fMax;
				}
			}
		}
		return max;
	}
	
	public float calcolaCostoFattura(Fattura f) {
		float c = 0;
		LinkedList<Integer> quantita = f.getQuantita();
		LinkedList<String> prodotti = f.getProdotti();
		for (int i = 0; i < prodotti.size(); i++) {
			Prodotto p = getProdotto(prodotti.get(i));
			c += p.getPrezzoUnitario() + quantita.get(i);
		}
		return c;
	} 
	
	public Prodotto getProdotto(String nome) {
		for(Prodotto p : prodotti) {
			if (p.getNome().equals(nome)) {
				return p;
			}
		}
		return null;
	}
}
