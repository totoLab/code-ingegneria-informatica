package esercitazioni.polinomi;

public class Test {
	
	public static void main(String...args) {
		Monomio m1 = new Monomio(1, 0);
		Monomio m2 = new Monomio(2, 0);
		Polinomio p1 = new PolinomioLL();
		Polinomio p2 = new PolinomioLL();
		p1.add(m1);
		p2.add(m2);
		
		Polinomio p3 = p1.add(p2);
		System.out.println(p3);
		
		String path = "/inserisci path /polinomio.txt";
		p3.save(path);
		p3.restore(path);
		
	}

}
