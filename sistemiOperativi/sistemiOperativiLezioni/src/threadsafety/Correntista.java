package threadsafety;

import java.util.Random;

public class Correntista implements Runnable {
	
	private final static int MIN_ATTESA = 1;
	private final static int MAX_ATTESA = 3;
	
	private Random random = new Random();
	private ContoCorrente cc;
	private int importo;
	private int numOperazioni;
	
	public Correntista(ContoCorrente cc, int importo, int numOperazioni) {
		if (numOperazioni % 2 != 0) {
			throw new RuntimeException(
					"Il numero di operazioni deve essere pari");
		}
		this.cc = cc;
		this.importo = importo;
		this.numOperazioni = numOperazioni;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < numOperazioni; i++) {
				attesaCasuale();
				if (i % 2 == 0) {
					cc.deposita(importo);
				} else {
					cc.preleva(importo);
				}
			}
		} catch (InterruptedException e) {}
		System.out.println("Correntista " + Thread.currentThread().getId() + " ha terminato le sue operazioni");
	}

	private void attesaCasuale() throws InterruptedException {
		Thread.sleep((random.nextInt(
				MAX_ATTESA - MIN_ATTESA + 1) + MIN_ATTESA));
	}
	
}
