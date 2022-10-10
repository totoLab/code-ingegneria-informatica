package poo.geometria;

public class Cilindro extends Disco{
	private double altezza;
	public Cilindro( double raggio, double altezza ) {
		super(raggio);
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=altezza;
	}
	public Cilindro( double x, double y, double raggio, double altezza ) {
		super(x,y,raggio);
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=altezza;
	}
	public Cilindro( Cilindro c ) {
		super( c.getX(), c.getY(), c.getRaggio() );
		this.altezza=c.altezza;
	}
	
	public double getAltezza() { return altezza; }
	
	@Override
	public double perimetro() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public double area() {
		return 2*super.area()+super.perimetro()*altezza;
	}//area
	
	public double areaDiBase() {
		return super.area();
	}//areaDiBase
	
	public double areaLaterale() {
		return super.perimetro()*altezza;
	}//areaLaterale
	
	public double volume() {
		return areaDiBase()*altezza;
	}//volume
	
	@Override
	public String toString() {
		return "Cilindro di base "+super.toString()+" e altezza "+altezza;
		
	}//toString
	
}//Cilindro
