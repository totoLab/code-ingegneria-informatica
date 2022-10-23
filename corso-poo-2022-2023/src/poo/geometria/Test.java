package poo.geometria;

public class Test {

	public static void main(String[] args) {

		Punto p = new Punto(10,10);
		
		Disco d = new Disco(10,4,67);
		
		d.toString();
		
		p = d;
		Punto p2 = new Disco(12,45,89);
		
		System.out.println(p);
		
		p2.getClass();

	}

}
