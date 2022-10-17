package poo.anagrafe;

import java.util.Iterator;

public class Anagrafe {
	
	private Persona[] elenco;
	private int numeroPersone = 0;
	
	private final boolean dimDinamica;
	
		public Anagrafe() {
		this(100);
	}
	
	public Anagrafe(int dim) {
		this(dim, false);
	}
	
	public Anagrafe(int dim, boolean dimDinamica) {
		if (dim <= 0) throw new IllegalArgumentException();
		elenco = new Persona[dim];
		numeroPersone = 0;
		this.dimDinamica = dimDinamica;
	}
	
	public Anagrafe(Persona[] elenco, int dim) {
		this(elenco, dim, false);
	}
	
	public Anagrafe(Persona[] elenco, int dim, boolean dimDinamica) {
		if (dim >= elenco.length) throw new IllegalArgumentException();
		this.elenco = new Persona[dim];
		
		for (int i = 0; i < elenco.length; i++) {
			this.elenco[i] = elenco[i];
		}
		numeroPersone = elenco.length;
		this.dimDinamica = dimDinamica;
	}
	
	public Anagrafe(Anagrafe a) {
		this(a.elenco, a.numeroPersone, false);
	}
	
	public boolean aggiungi(Persona p) {
		if (numeroPersone >= elenco.length) {
			if (dimDinamica) {
				resize(); // piena ed espandibile
			} else {
				return false; // piena e BASTA
			}
		}
			
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
		if (dimDinamica) return false;
		return elenco.length == numeroPersone;
	}
	
	private void resize() {
		Persona[] nuovoElenco = new Persona[elenco.length * 2];
		System.arraycopy(elenco, 0, nuovoElenco, 0, numeroPersone);
		this.elenco = nuovoElenco;
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
