package esercitazioni.gestioneaffittacamere2;

import java.util.HashMap;
import java.util.Objects;

import poo.util.Data;

public class Stanza {
	private int numPosti;
	private HashMap<Data, Boolean> dateOccupata; ///data : true - occupata, false - libera
	private double prezzo;
	private String nome;
	
	public Stanza(int numPosti, double prezzo, String nome) {
		this.numPosti = numPosti;
		this.dateOccupata = new HashMap<Data, Boolean>();
		this.prezzo = prezzo;
		this.nome = nome;
	}
	
	public Stanza(Stanza stanza) {
		this(stanza.numPosti, stanza.prezzo, stanza.nome);
		this.dateOccupata = new HashMap<>(stanza.dateOccupata);
	}
	
	public int getNumPosti() {
		return numPosti;
	}
	
	private void setNumPosti(int numPosti) {
		if (!(numPosti > 0)) throw new IllegalArgumentException();
		this.numPosti = numPosti;
	}
	
	public boolean stanzaLibera(Data data) {
		if (dateOccupata.containsKey(data)) {
			boolean occupata = dateOccupata.get(data);
			if (occupata)
				return false;
		} else {
			dateOccupata.put(data, false);
		}
		return true;
	}
	
	private void setLibera(Data data) {
		if (dateOccupata.containsKey(data)) {
			boolean occupata = dateOccupata.get(data);
			if (occupata) {
				// handle prenotazioni -> cancella prenotazioni?
			} else {
				// give a warning?
				System.out.println("Stanza già libera");
			}
		}
	}
	
	private void occupaStanza(Data data) {
		dateOccupata.put(data, true);
	}
	
	public void effettuaPrenotazione(Data dataInizio, Data dataFine) {
		for (Data data = new Data(dataInizio); !data.equals(dataFine); data.giornoDopo()) {
			if (!(this.stanzaLibera(data))) {
				throw new IllegalStateException("Stanza già occupata in data " + data.toString());
			}
		}
		for (Data data = new Data(dataInizio); !data.equals(dataFine); data.giornoDopo()) {
			occupaStanza(data);
		}
	}

	public double getPrezzo() {
		return prezzo;
	}
	
	private void setPrezzo(double prezzo) {
		if (prezzo < 0) throw new IllegalArgumentException();
		this.prezzo = prezzo;
	}
	
	public String getNome() {
		return nome;
	}

	public String toString() {
		return "Stanza <" + nome + ">, posti " + numPosti + ", prezzo €" + prezzo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateOccupata, nome, numPosti, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Stanza)) return false;
		Stanza other = (Stanza) obj;
		return this.nome.equals(other.nome);
	}
	
}
