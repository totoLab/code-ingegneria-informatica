package traccia17062022;

import java.util.*;

public class Prenotazione {

	private LinkedList<String> percorso;
	private String nomeCliente;
	private String classe;
	
	public Prenotazione(LinkedList<String> percorso, String nomeCliente, String classe) {
		this.percorso = percorso;
		this.nomeCliente = nomeCliente;
		this.classe = classe;
	}

	public LinkedList<String> getPercorso() {
		return new LinkedList<>(percorso);
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getClasse() {
		return classe;
	}
	
	public String toString() {
		String ret = "Prenotazione di " + nomeCliente + " in classe " + classe + " con percorso:\n";
		for (String s : percorso) {
			ret += s + " -> ";
		}
		return ret.substring(0, ret.length() - 4);
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Prenotazione)) return false;
		
		Prenotazione p = (Prenotazione) o;
		return this.percorso.equals(p.percorso) &&
				this.nomeCliente.equals(p.nomeCliente) &&
				this.classe.equals(p.classe);
	}
}
