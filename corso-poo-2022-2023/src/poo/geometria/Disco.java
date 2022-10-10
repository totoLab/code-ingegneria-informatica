package poo.geometria;

public class Disco extends Punto{
	private double raggio;
	public Disco( double raggio ) {
		super(); //invoca Punto()
		if( raggio<=0 ) throw new IllegalArgumentException("Raggio non positivo.");
		this.raggio=raggio;
	}
	public Disco( double x, double y, double raggio ) {
		super(x,y); //invoca Punto(x,y)
		if( raggio<=0 ) throw new IllegalArgumentException("Raggio non positivo.");
		this.raggio=raggio;
	}
	public Disco( Disco d ) {
		super(d.getX(),d.getY());
		this.raggio=d.raggio;
	}
	
	public double getRaggio() { return raggio; }
	public double perimetro() {
		return 2*Math.PI*raggio;
	}//perimetro
	public double area() {
		return raggio*raggio*Math.PI;
	}//area
	
	//override o ridefinizione
	@Override
	public String toString() {
		String s="Disco di centro: "+super.toString()+" e raggio="+raggio;
		return s;
	}//toString
	
	public static void main( String[] args ) {
		Punto p=new Punto(3,5);
		Disco d=new Disco( 7 );
		System.out.println("p="+p);
		System.out.println("d="+d);
		double dist=p.distanza(d);
		System.out.println("dist="+dist);
		System.out.println("x="+d.getX());
		d.muovi(2, 7);
		dist=d.distanza(p);
		System.out.println("Dopo muovi dist="+dist);
		System.out.println("perimetro="+d.perimetro()+" area="+d.area());
		
		p=d;
		System.out.println(p);
		
		if( p instanceof Punto ) {
			System.out.println("p è di tipo Punto ...");
		}
		
		if( p instanceof Disco ) {
			Disco ds=(Disco)p;
			System.out.println("area="+ds.area());
		}
		
		d=(Disco)p;
		
	}//main
	
}//Disco
