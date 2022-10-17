package poo.anagrafe;

import java.util.Iterator;

public class Anagrafe {
	
	private Persona[] elenco;
	private int numeroPersone = 0;
	
	public Anagrafe() {
		this(100);
	}
	
	public Anagrafe(int dim) {
		if (dim <= 0) throw new IllegalArgumentException();
		elenco = new Persona[dim];
		numeroPersone = 0;
	}
	
	public Anagrafe(Persona[] elenco, int dim) {
		if (dim >= elenco.length) throw new IllegalArgumentException();
		this.elenco = new Persona[dim];
		
		for (int i = 0; i < elenco.length; i++) {
			this.elenco[i] = elenco[i];
			
		numeroPersone = elenco.length;
		}
	}
	
	public Anagrafe(Anagrafe a) {
		this(a.elenco, a.numeroPersone);
	}
	
	public boolean aggiungi(Persona p) {
		if (numeroPersone >= elenco.length) return false;
		for (int i = 0; i < numeroPersone; i++) {
			if (p.getNome().equals(elenco[i].getNome())
					&& p.getCognome().equals(elenco[i].getCognome())) {
				return false;
			}
		}
		this.elenco[numeroPersone] = p;
		numeroPersone++;
		return true;
	}
	
	public int getNumeroPersone() {
		return numeroPersone;
	}
	
	public boolean anagrafePiena() {
		return elenco.length == numeroPersone;
	}
	
	public int trovaIndice(Persona p) {
		int index = -1;
		for (int i = 0; i < elenco.length; i++) {
			if (p.getNome().equals(elenco[i].getNome())
					&& p.getCognome().equals(elenco[i].getCognome())) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean trova(Persona p) {
		for (int i = 0; i < elenco.length; i++) {
			if (p.getNome().equals(elenco[i].getNome())
					&& p.getCognome().equals(elenco[i].getCognome())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean rimuoviSmart(Persona p) {
		if (numeroPersone >= elenco.length) return false;
		int posizione = trovaIndice(p);
		if (posizione != -1) {
			elenco[posizione] = elenco[numeroPersone];
			elenco[numeroPersone] = null;
			numeroPersone--;
			return true;
		}
		return false;
	}
	
	public boolean rimuovi(Persona p) {
		int index = -1;
		for (int i = 0; i < elenco.length; i++) {
			if (p.getNome().equals(elenco[i].getNome())
					&& p.getCognome().equals(elenco[i].getCognome())) {
				index = i;
				break;
			}
		}
		if (index == -1) return false;
		
		for (int i = index; i < numeroPersone - 1; i++) {
			elenco[i] = elenco[i + 1];
		}
		elenco[numeroPersone - 1] = null;
		numeroPersone--;
		return true;
	}
	
	public Persona[] ricerca(String cognome) {
		
		return null;
	}
	
}
