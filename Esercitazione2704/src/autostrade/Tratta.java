package autostrade;

public class Tratta {
	
	private String codice, nome;
	private Citta cittaPartenza, cittaDestinazione;
	private double distanza;
	
	public Tratta(String codice, String nome, Citta cittaPartenza, Citta cittaDestinazione, double distanza) {
		this.codice = codice;
		this.nome = nome;
		this.cittaPartenza = cittaPartenza; // senza protective copy perché genera oggetti immutabili
		this.cittaDestinazione = cittaDestinazione;
		//this.cittaPartenza = new Citta(cittaPartenza.getNome(), cittaPartenza.getProvincia(), cittaPartenza.getRegione());
		//this.cittaDestinazione = new Citta(cittaDestinazione.getNome(), cittaDestinazione.getProvincia(), cittaPartenza.getRegione());
		this.distanza = distanza;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Citta getCittaPartenza() {
		return cittaPartenza;
	}
	
	public Citta getCittaDestinazione() {
		return cittaDestinazione;
	}
	
	public double getDistanza() {
		return distanza;
	}
	
	public String toString() {
		return "Tratta con codice " + codice + " tra " + cittaPartenza.toString() + " e " + cittaDestinazione.toString();
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Tratta)) {
			return false;
		}
		
		Tratta t = (Tratta) o;
		return this.codice.equals(t.codice);
	}
}
