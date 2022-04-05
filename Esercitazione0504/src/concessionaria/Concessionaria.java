package concessionaria;

import java.util.*;
import terminale.*;

public class Concessionaria { // mutabile
	
	private ArrayList<Automobile> magazzino;
	
	public Concessionaria () {
		this.magazzino = new ArrayList<>();
	}
	
	public void aggiungiAuto(Automobile a) {
		magazzino.add(a);
	}
	
	public void eliminaAuto(Automobile a) {
		magazzino.remove(a);
	}
	
	public void stampaAutoMagazzino() {
		for (Automobile a : magazzino) {
			Terminale.stampa(a);
		}
	}
	
	public float sommaPrezziAuto() {
		float somma = 0;
		for (Automobile a : magazzino) {
			somma += a.getPrezzo();
		}
		return somma;
	}
	
	public String casaProduttricePiuRicorrente() {
		ArrayList<String> caseProduttrici = estraiCase();
		String casaPiuRic = caseProduttrici.get(0); // oppure null
		int maxOcc = occorrenzeCasa(casaPiuRic); // oppure null
		
		for (int i = 1; i < caseProduttrici.size(); i++) {
			String casa = caseProduttrici.get(i);
			int numOcc = occorrenzeCasa(casa);
			if (maxOcc > maxOcc) {
				maxOcc = numOcc;
				casaPiuRic = casa;
			}
		}
		return casaPiuRic;
	}
	
	private int occorrenzeCasa(String casa) {
		int c = 0;
		for (Automobile a : magazzino) {
			if (a.getCasaProduttrice().equals(casa)) {
				c += 1;
			}
		}
		return c;
	}
	
	private ArrayList<String> estraiCase() {
		ArrayList<String> ris = new ArrayList<>();
		for (Automobile a : magazzino) {
			String casa = a.getCasaProduttrice();
			if (!ris.contains(casa)) {
				ris.add(casa);
			}
		}
		return ris;
	}
	
	public Automobile cilindrataBassa() {
		Automobile ris = null;
		for (Automobile a : magazzino) {
			if (a.getPrezzo() < 10000 && 
					(ris == null || a.getCilindrata() < ris.getCilindrata())) {
				ris = a;
			}
		}		
		return ris;
	}
	
	public Automobile cilindrataAlta() {
		float media = mediaPrezzi();

		Automobile ris = null;
		for (Automobile a : magazzino) {
			if (a.getPrezzo() > media && 
					(ris == null || a.getCilindrata() > ris.getCilindrata())) {
				ris = a;
			}
		}		
		return ris;
	}
	
	private float mediaPrezzi() {
		float media = 0;
		
		return media;
	}	
}
