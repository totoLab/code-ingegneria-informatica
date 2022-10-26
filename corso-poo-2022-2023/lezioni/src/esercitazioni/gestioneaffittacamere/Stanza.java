package esercitazioni.gestioneaffittacamere;

public class Stanza {
	private int numPosti;
	private boolean libera;
	private double prezzo;
	private String nome;
	
	public Stanza(int numPosti, double prezzo, String nome) {
		this.numPosti = numPosti;
		this.libera = true;
		this.prezzo = prezzo;
		this.nome = nome;
	}
	
	public int getNumPosti() {
		return numPosti;
	}
	
	private void setNumPosti(int numPosti) {
		if (!(numPosti > 0)) throw new IllegalArgumentException();
		this.numPosti = numPosti;
	}
	
	public boolean getLibera() {
		return libera;
	}
	
	public void effettuaPrenotazione() {
		if (libera) {
			this.libera = false;
		} else {
			throw new IllegalStateException("Stanza già occupata");
		}
	}
	
	public void liberaStanza() {
		this.libera = true;
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

}
