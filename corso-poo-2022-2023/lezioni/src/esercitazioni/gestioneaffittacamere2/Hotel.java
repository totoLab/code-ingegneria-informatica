package esercitazioni.gestioneaffittacamere2;

import java.util.ArrayList;

import poo.anagrafe.Persona;
import poo.util.Data;

public class Hotel {
	private ArrayList<Stanza> stanze;
	private ArrayList<Prenotazione> prenotazioni;
	private String nome;
	private int numStelle;
	private String via;
	private String citta;

	
	public Hotel(ArrayList<Stanza> stanze, ArrayList<Prenotazione> prenotazioni, 
			String nome, int numStelle, String via, String citta) {
		this.stanze = stanze;
		for (Prenotazione p : prenotazioni) {
			Stanza stanzaPrenotata = p.getStanza();
			if (stanze.contains(stanzaPrenotata))
				this.prenotazioni.add(new Prenotazione(p, stanzaPrenotata));
		}
		this.nome = nome;
		this.numStelle = numStelle;
		this.via = via;
		this.citta = citta;
	}

	public void aggiungiPrenotazione(ArrayList<Persona> prenotanti, Stanza stanza, Data dataInizio, Data dataFine) {
		prenotazioni.add(
				new Prenotazione(prenotanti, stanza, dataInizio, dataFine));
	}
	
	public void aggiungiStanza(Stanza s1) {
		for (Stanza s2 : stanze) {
			if (s1.equals(s2)) {
				throw new IllegalArgumentException("Stanza già presente nel sistema");
			}
		}
		stanze.add(s1);
	}

	public ArrayList<Stanza> getStanze() {
		return new ArrayList<>(stanze);
	}

	public ArrayList<Prenotazione> getPrenotazioni() {
		return new ArrayList<>(prenotazioni);
	}
	
}
