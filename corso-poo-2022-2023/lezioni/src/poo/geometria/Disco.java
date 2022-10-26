package poo.geometria;

public class Disco extends Punto {
	
	private double raggio;
	
	public Disco(double raggio) {
		super();
		this.raggio=raggio;
	}
	
	public Disco(double x, double y, double raggio) {
		super(x,y);
		this.raggio=raggio;
	}
	public Disco(Punto centro, double raggio) {
		super(centro.getX(),centro.getY());
		this.raggio=raggio;
	}
	public Disco(Disco c) {
		super(c);
		this.raggio=c.raggio;
	}
	public double getRaggio() {
		return raggio;
	}
	public double getArea() {
		return raggio*raggio*Math.PI;
	}
	public double getPerimetro() {
		return 2*raggio*Math.PI;
	}
	public String toString() {
		return super.toString()+", raggio:"+raggio;
	}
	public boolean equals(Object o) {
		if (!(o instanceof Disco))
			return false;		
		Disco d =(Disco)o;
		return d.getX()==this.getX() && d.getY()==this.getY() 
				&& d.getRaggio()==this.getRaggio();
	}
}
