package razionali;

public class EccezioneDenominatoreZero extends RuntimeException {
	
	public String toString() {
		return "Tentativo di costruzione di un Razionale con denominatore zero.";
	}
}
