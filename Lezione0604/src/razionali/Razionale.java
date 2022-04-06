package razionali;

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
	
	private void semplifica () {
		if (numeratore == 0) {
			denominatore = 1;
		} else {
			int mcd = massimoComunDivisore(Math.abs(numeratore), denominatore);
			numeratore /= mcd;
			denominatore /= mcd;
		}
	}
	
	private int massimoComunDivisore(int a, int b)
	{	for(int v = Math.min(a, b); ;v--)
			if(a % v == 0 && b % v == 0)
				return v;	
	}
	
	public void moltiplica(Razionale r) {
		this.numeratore *= r.numeratore;
		this.denominatore *= r.denominatore;
		
		semplifica();
	}
	
	public void moltiplica(int x) {
		moltiplica(new Razionale(x));
		
		// alternativa:
		// numeratore *= x;
		// semplifica();
	}
	
	public Razionale reciproco() {
		return new Razionale(denominatore, numeratore);
	}
	
	public void dividi(Razionale r) {
		moltiplica(r.reciproco());
	}
	
	public void aggiungi(Razionale r) {
		numeratore = numeratore * r.denominatore + denominatore * r.numeratore;
		denominatore *= r.denominatore;
		
		semplifica();
	}
	
	public String toString()
	{	if(denominatore == 1)
			return ""+numeratore;
		return numeratore+"/"+denominatore;
	}

	
	
}
