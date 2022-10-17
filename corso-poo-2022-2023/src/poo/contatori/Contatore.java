package poo.contatori;

public class Contatore {
	
	protected int valore; // subclass visible
	
	public Contatore() {
		this.valore = 0;
	}
	
	public Contatore(int valore) {
		this.valore = valore;
	}
	
	public Contatore(Contatore c) {
		if (c == null) throw new IllegalArgumentException();
		this.valore = c.valore;
	}
	
	public int getValore() {
		return valore;
	}
	
	public void incr() {
		valore++;
	}
	
	public void decr() {
		valore--;
	}
	
	@Override
	public String toString() {
		return "Contatore: " + valore;
	}
	
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Contatore)) return false;
		
		Contatore c = (Contatore) o;
		return this.valore == c.valore;
	}
	
	
}
