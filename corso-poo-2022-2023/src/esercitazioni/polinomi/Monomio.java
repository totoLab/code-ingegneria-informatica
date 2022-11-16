package esercitazioni.polinomi;

public class Monomio implements Comparable<Monomio> { // immutabile
	
	private final int COEFFICIENTE;
	private final int GRADO;
	
	public Monomio(int coefficiente, int grado) {
		if (grado < 0) throw new IllegalArgumentException();
		this.COEFFICIENTE = coefficiente;
		this.GRADO = grado;
	}
	
	public Monomio(Monomio m) {
		if (m == null) throw new IllegalArgumentException(); 
		this.COEFFICIENTE = m.COEFFICIENTE;
		this.GRADO = m.GRADO;
	}

	public int getCoefficiente() {
		return COEFFICIENTE;
	}

	public int getGrado() {
		return GRADO;
	}
	
	public Monomio add(Monomio m) {
		if (m == null || this.GRADO != m.GRADO) throw new IllegalArgumentException();
		return new Monomio(this.COEFFICIENTE * m.getCoefficiente(), this.GRADO);
	}
	
	public Monomio mul(int coefficiente) {
		return new Monomio(this.COEFFICIENTE * coefficiente, this.GRADO);
	}
	
	public Monomio mul(Monomio m) {
		if (m == null) throw new IllegalArgumentException();
		return new Monomio(this.COEFFICIENTE * m.getCoefficiente(), this.GRADO + m-getGrado());
	}
	
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Monomio)) return false;
		Monomio m = (Monomio) o;
		return this.GRADO == m.getGrado();
	}
	
	public int compareTo(Monomio o) {
		if (o == null) throw new IllegalArgumentException();d
		if (this.GRADO < o.getGrado()) return -1;
		if (this.GRADO > o.getGrado()) return 1;
		if (this.COEFFICIENTE < o.getCoefficiente()) return -1;
		if (this.COEFFICIENTE > o.getCoefficiente()) return 1;
		return 0;
	}
	
}
