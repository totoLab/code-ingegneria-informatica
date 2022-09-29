public class Triangolo{
	private Punto p1,p2,p3; //variabili di istanza o campi 2- stato di un punto
	private double a, b, c;

	public Triangolo( Punto p1, Punto p2, Punto p3){ //costruttore normale
		a=p1.distanza(p2);
		b=p2.distanza(p3);
		c=p3.distanza(p1);
		if( a>=b+c || b>=a+c || c>=a+b )
			throw new IllegalArgumentException("Triangolo insistente.");
		this.p1=new Punto(p1); this.p2=new Punto(p2); this.p3=new Punto(p3); // NO aliasing
	}
	public Triangolo( Triangolo t ){ //costruttore di copia
		p1=new Punto(t.p1); p2=new Punto(t.p2); p3=new Punto(t.p3);
		a=t.a; b=t.b; c=t.c;
	}
	//metodi accessori getter
	public Punto[] getVertici(){
		Punto[] v=new Punto[3]; /*un array di 3 punti*/
		v[0]=new Punto(p1); v[1]=new Punto(p2); v[2]=new Punto(p3);
		return v;
	}//getVertici

    public String tipo(){
		return "NON SO"; //TODO
	}//tipo

	public double perimetro(){
		return a+b+c;
	}//perimetro

	public double area(){
		double s=perimetro()/2;
		return Math.sqrt( s*(s-a)*(s-b)*(s-c) );
	}//area

	public String toString(){
		return "Triangolo("+p1+","+p2+","+p3+")"; //return "x="+x+" y="+y;
    }//toString

	public static void main( String[] args ){
		Punto p=new Punto();
		Punto q=new Punto(7,-4);
		Punto r=new Punto(12,10);
		Triangolo t=new Triangolo(p,q,r);
		System.out.println(t);
		p.muovi( -3, 5 );
		System.out.println(t);
		System.out.println("area="+t.area()+" perimetro="+t.perimetro());
	}//main

}//Triangolo