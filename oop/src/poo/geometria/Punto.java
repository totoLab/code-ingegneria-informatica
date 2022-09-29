public class Punto{
	private double x, y; //variabili di istanza o campi 2- stato di un punto
	public Punto(){//costruttore di default
		this(0,0);
	}
	public Punto( double x, double y ){ //costruttore normale
		this.x=x; this.y=y;
	}
	public Punto( Punto p ){ //costruttore di copia
		//x=p.x; y=p.y;
		this(p.x,p.y);
	}
	//metodi accessori getter
	public double getX(){ return this.x; }
	public double getY(){ return this.y; }
	//metodo mutatore
	public void muovi( double x, double y ){
		this.x=x; this.y=y;
	}
	public double distanza( Punto p ){
		return Math.sqrt( (this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y) );
	}

	public static double distanza( Punto p1, Punto p2 ){
		//return Math.sqrt( (p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()) );
		//piu' semplicemente:
		return p1.distanza(p2);
	}

	public String toString(){
		return "Punto("+x+","+y+")"; //return "x="+x+" y="+y;
      }

	public static void main( String[] args ){
		Punto p1=new Punto(5,7);
		Punto p2=new Punto();
		Punto p3=new Punto( p1 );
		System.out.println("p1.x="+p1.getX()+" p1.y="+p1.getY());
		System.out.println(p1);
		p3.muovi(12,15);
		System.out.println("p1.x="+p1.getX()+" p1.y="+p1.getY());
		System.out.println("p3.x="+p3.getX()+" p3.y="+p3.getY());
		System.out.printf( "distanza tra p1 e p3 = %1.2f%n", p1.distanza(p3) );
		System.out.printf( "distanza tra p1 e p3 = %1.2f%n", Punto.distanza(p1,p3) );
	}

}//Punto