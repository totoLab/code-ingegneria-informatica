package poo.figure;
import poo.util.Mat;
public class Rettangolo extends Figura {
	private double base;
	public Rettangolo(double base, double altezza) {
		super(altezza);
		this.base=base;
	}
	public double getBase() {return base;}
	public double getAltezza() {return getDimensione();}
	@Override
	public double getArea() {
		return getBase()*getAltezza();
	}
	@Override
	public double getPerimetro() {
		return 2*getBase()+2*getAltezza();
	}
	@Override
	public String toString() {
		return "Rettangolo: base "+this.getBase()+" - altezza "+getAltezza();
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Rettangolo))
			return false;
		if (this==obj)
			return true;
		Rettangolo c = (Rettangolo)obj;
		return Mat.sufficientementeProssimi(getBase(),c.getBase()) &&
				Mat.sufficientementeProssimi(getAltezza(), c.getAltezza());
	}

}
