package conticorrenti;

public class Movimento { // immutabile
	private String tipo;
	private float valore;
	
	public Movimento(String tipo, float valore) {
		this.tipo = tipo;
		this.valore = valore;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	public float getValore() {
		return this.valore;
	}
	
	public String toString() {
		return "Movimento di tipo " + tipo + " e valore " + valore;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Movimento)) {
			return false;
		}
		
 		Movimento m = (Movimento) o;
 		return this.tipo.equals(m.tipo) && this.valore == m.valore;
	}
	
}