package esercitazioni.gestioneaffittacamere2;

import java.util.ArrayList;
import java.util.Objects;

import poo.anagrafe.Persona;
import poo.util.Data;

public class Prenotazione {

	private ArrayList<Persona> prenotanti;
	private Stanza stanza;
	private Data dataInizio;
	private Data dataFine;
	
	public Prenotazione(ArrayList<Persona> prenotanti, Stanza stanza, Data dataInizio, Data dataFine) {
		if (prenotanti.size() < stanza.getNumPosti()) {
			this.prenotanti = new ArrayList<>(prenotanti);
			this.stanza = stanza;
			stanza.effettuaPrenotazione(dataInizio, dataFine);
		} else {
			throw new IllegalArgumentException("Numero di prenotanti eccede il numero di posti disponibili");
		}
	}
	
	public Prenotazione(Prenotazione p, Stanza stanza) {
		this(p.prenotanti, stanza, p.dataInizio, p.dataFine);
	}

	public ArrayList<Persona> getPrenotanti() {
		return new ArrayList<>(prenotanti);
	}

	public Stanza getStanza() {
		return new Stanza(stanza);
	}

	public Data getDataInizio() {
		return dataInizio;
	}

	public Data getDataFine() {
		return dataFine;
	}
	
	public String toString() {
		String s = "Prenotazione effettuata per la stanza " + stanza
				+ " dalle seguenti persone: \n";
		for (Persona p : prenotanti) {
			s += "  " + p.getNome() + " " + p.getCognome() + ";\n";
		}
		s += "dal " + dataInizio + " al " + dataFine;
		
		return s;
	}
	
	public int hashCode() {
		return Objects.hash(prenotanti, stanza, dataInizio, dataFine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Prenotazione)) return false;
		Prenotazione other = (Prenotazione) obj;
		return this.prenotanti.equals(other.prenotanti) &&
				this.stanza.equals(other.stanza) &&
				this.dataInizio.equals(other.dataInizio) &&
				this.dataFine.equals(other.dataFine);
	}
	
}
