

public class Biglietto {
	private int numero; // attributo oggetto
	private static int bigliettiCreati = 0; // attributo classe
	
	public Biglietto() {
		this.numero = Biglietto.bigliettiCreati + 1;
		Biglietto.bigliettiCreati++;
	}
	
	public String toString() {
		return "Biglietto numero " + this.numero;
	}
}
