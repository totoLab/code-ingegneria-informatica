package poo.figure;

public abstract class Figura {
	
	public abstract double getArea();
	public abstract double getPerimetro();
	
	private double dimensione;
	
	protected double getDimensione() {return dimensione;}
	
	public Figura(double dimensione) {
		this.dimensione=dimensione;
	}
	
	public static Figura getAreaMassima(Figura... f) {
		int iAreaMax=0;
		for(int i=1; i<f.length; i++) {
			if (f[iAreaMax].getArea()<f[i].getArea())
				iAreaMax=i;
		}
		return f[iAreaMax];
	}

}
