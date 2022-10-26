package gestioneaffittacamere;

public class Stanza {
	private int numPosti;
	private boolean libera;
	private double prezzo;
	private String nome;
	
	
	public Stanza(int numPosti, double prezzo, String nome) {
		this.numPosti=numPosti;
		this.libera=true;
		this.prezzo=prezzo;
		this.nome=nome;
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
		this.libera=true;
	}
	
	// TODO getters and setters

}
