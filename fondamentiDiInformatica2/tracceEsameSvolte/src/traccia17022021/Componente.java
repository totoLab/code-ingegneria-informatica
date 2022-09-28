package traccia17022021;

public class Componente {

	private String codice;
	private float prezzo;
	
	public Componente(String codice, float prezzo) {
		this.codice = codice;
		this.prezzo = prezzo;
	}

	public String getCodice() {
		return codice;
	}

	public float getPrezzo() {
		return prezzo;
	}
	
	public String toString() {
		return "Componente con codice " + codice + " e prezzo " + prezzo;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Componente)) return false;
		
		Componente c = (Componente) o;
		return this.codice.equals(c.codice);
	}
}
