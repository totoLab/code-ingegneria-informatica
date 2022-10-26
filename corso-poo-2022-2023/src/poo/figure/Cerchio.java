package poo.figure;

import poo.util.Mat;

public class Cerchio extends Figura {
	
	public Cerchio(double raggio) {
		super(raggio);
	}

	public double getRaggio() {
		return getDimensione();
	}
	
	@Override
	public double getArea() {
		double r=getRaggio();
		return r*r*Math.PI;
	}

	@Override
	public double getPerimetro() {
		double r=getRaggio();
		return 2*r*Math.PI;
	}
	
	@Override
	public String toString() {
		return "Cerchio: raggio"+getRaggio();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cerchio))
			return false;
		if (this==obj)
			return true;
		Cerchio c = (Cerchio)obj;
		return Mat.sufficientementeProssimi(getArea(),c.getArea());
	}

}
