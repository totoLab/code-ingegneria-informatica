package esercitazioni.polinomi;

public class Monomio { // immutabile
	
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
	
	
	
	
}
