package threadLifeCicle;

public class StampanteT extends Thread {
	
	public int da, a;
	
	public StampanteT(int da, int a) {
		this.da = da;
		this.a = a;
	}
	
	@Override
	public void run() {
		for (int i = da; i <= a; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
