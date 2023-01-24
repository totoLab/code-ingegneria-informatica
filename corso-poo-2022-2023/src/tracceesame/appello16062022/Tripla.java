package tracceesame.appello16062022;

public class Tripla<T> {

	final double x, y;
	final T valore;
	
	public Tripla(double x, double y, T valore) {
		this.x = x;
		this.y = y;
		this.valore = valore;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public T getValore() {
		return valore;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Tripla)) return false;
		
		Tripla<T> t = (Tripla<T>) o;
		return this.valore.equals(t.valore);
	}
	
	@Override
	public int hashCode() {
		int prime = 31;
		int h = 0;
		h = h + (int) (prime * x);
		h = h + (int) (prime * y);
		h = h + prime * valore.hashCode();
		return h;
	}
	
	@Override
	public String toString() {
		return valore.toString() + " nella posizione (" + x + ", " + y+ ")";
	}
	
}
