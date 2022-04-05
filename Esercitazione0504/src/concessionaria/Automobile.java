package concessionaria;

public class Automobile { // immutabili
	private String modello;
	private String colore;
	private String casaProduttrice;
	private int cilindrata;
	private float prezzo;
	private String numTelaio;
	
	public Automobile (String modello, String colore, String casaProduttrice,
			int cilindrata, float prezzo, String numTelaio) {
		this.modello = modello;
		this.colore = colore;
		this.casaProduttrice = casaProduttrice;
		this.cilindrata = cilindrata;
		this.prezzo = prezzo;
		this.numTelaio = numTelaio;
	}
	
	public String getModello() {
		return this.modello;
	}
	
	public String getColore() {
		return this.colore;
	}
	
	public String getCasaProduttrice() {
		return this.casaProduttrice;
	}
	
	public int getCilindrata() {
		return this.cilindrata;
	}
	
	public float getPrezzo() {
		return this.prezzo;
	}
	
	public String getNumTelaio() {
		return this.numTelaio;
	}

	public String toString() {
		return casaProduttrice + " " + modello + ", telaio: " + numTelaio;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Automobile)) {
			return false;
		}
		
		Automobile a = (Automobile) o;
		
		return this.numTelaio.equals(a.numTelaio);
	}
	
}

