package threadsafety;

public class Correntista implements Runnable {

	private final static int MIN_ATTESA = 1;
	private final static int MAX_ATTESA = 3;

	
	@Override
	public void run() {
		try {
			for (int i = 0; i < numeroOperazioni; i++) {
				attesaCasuale();
				if (i % 2 == 0) {
					cc.deposita(importo);
				} else {
					cc.preleva(importo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
