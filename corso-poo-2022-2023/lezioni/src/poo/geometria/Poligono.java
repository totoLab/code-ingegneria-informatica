package poo.geometria;

public class Poligono {

	private Punto[] v ;
	
	public Poligono(Punto[] v) {
		if (v==null || v.length<3) {
			System.out.println("Errore");
			System.exit(-1);
		}
		this.v=new Punto[v.length];
		for (int i= 0;  i< v.length; i++) {
			this.v[i]=new Punto(v[i]);
		}
	}
	
	public Poligono(Poligono p) {
		this(p.v);		
	}
	
	public double area() {
		return -1;
	}
	
	public double perimetro() {
		return -1;
	}
	
	public String toString() {
		
		StringBuffer buffer= new StringBuffer();
		
		for (int i = 0; i < v.length; i++) {
			buffer.append(v[i]+" ");
		}
		
		return buffer.toString();
	}
	
	
	
}
