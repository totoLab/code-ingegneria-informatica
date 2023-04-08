package threadLifeCicle;

public class StampanteThreadTest {

	public static void main(String[] args) {
		
	}
	
	private void extension() {
		StampanteT s1 = new StampanteT(1, 10);
		StampanteT s2 = new StampanteT(11, 20);
		
		s1.start();
		s2.start();
		System.out.println("Fine");
	}
	
	private void implementation() {
		StampanteR s1 = new StampanteR(1, 10);
		StampanteR s2 = new StampanteR(11, 20);
		Thread t1 = new Thread(s1);
		Thread t2 = new Thread(s2);
		
		t1.start();
		t2.start();
		System.out.println("Fine");
	}
	
	private void stampanteTSequenziale() {
		StampanteT s1 = new StampanteT(1, 10);
		s1.start();
		
		try {
			s1.join(); // join rende l'output deterministico
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fine");
	}
}
