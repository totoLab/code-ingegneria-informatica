package poo.geometria;

public class Triangolo {
	
	private Punto p1,p2,p3;
	
	private double a,b,c;
	
	
	public Triangolo(Punto p1, Punto p2, Punto p3) {
		if (p1==null || p2==null || p3==null){
			System.out.println("Parametri errati");
			System.exit(-1);
		}
		this.p1=new Punto(p1);
		this.p2=new Punto(p2);
		this.p3=new Punto(p3);
		a = p1.distanza(p2);
		b = p2.distanza(p3);
		c = p3.distanza(p1);
		if (a>=b+c || b>=a+c || c>=a+b) {
			System.out.println("Trinagolo inesistente");
			System.exit(-1);
		}
	}
	
	/*
	public Triangolo(Triangolo t) {
		if (t==null) {
			System.out.println("Trinagolo inesistente");
			System.exit(-1);
		}
		p1=new Punto(t.p1);
		p2=new Punto(t.p2);
		p3=new Punto(t.p3);
		a=t.a;
		b=t.b;
		b=t.c;
	}*/
	public Triangolo(Triangolo t) {
		this(t.p1,t.p2,t.p3);
	}
	
	
	public double getA() {return a;}
	public double getB() {return b;}
	public double getC() {return c;}
	
	public double perimetro() {
		return a+b+c;
	}
	
	
	private double area=-1;
	public double area() {
		if (area>=0)
			return area;
		double s=this.perimetro()/2;
		area = Math.sqrt(  s*(s-a)*(s-b)*(s-c) );
		return area;
		
	}
	
	public String toString() {
		return "Triangolo con vertici "+p1+" "+p2+" "+p3;
	}
	
	public static void main(String arg[]) {
		
		Punto primo = new Punto(1,1);
		Punto secondo= new Punto(10,15);
		Punto terzo= new Punto(20,20);
		
		Triangolo t = new Triangolo(primo, secondo, terzo);
		
		System.out.println(t);
		System.out.println("Area: "+t.area()+" - Perimetro:"+t.perimetro());
		
		System.out.printf("Area=%1.2f - Perimetro=%1.2f",t.area(),t.perimetro());
	}
	

}
