
public class Razionale { // mutabile
	private int numeratore;
	private int denominatore; 
	
	
	public Razionale (int numeratore, int denominatore) {
		this.numeratore = numeratore;
		this.denominatore = denominatore;
		
		if (this.denominatore < 0) {
			this.numeratore *= -1;
			this.denominatore *= -1; // sempre positivo
		}
		
		semplifica(); // sempre ridotto ai minimi termini
	}
	
	public Razionale (int n) {
		this.numeratore = n;
		this.denominatore = 1;
		// alternativa:
		// this(n, 1); // chiama l'altro costruttore
	}
	
	public Razionale(Razionale r) {
		this.numeratore = r.numeratore;
		this.denominatore = r.denominatore;
		// this(r.numeratore, r.denominatore);
	}
	
	public Razionale() {
		this.numeratore = 0;
		this.denominatore = 1;
		// this(0, 1);
		// this(0);
	}
	
	private static void semplifica () {
		// TODO
	}

}
