import terminale.*;

public class Lezione {

	public static void main(String[] args) {
		Lampadina l1 = new Lampadina();
		Lampadina l2 = new Lampadina();
		
		l1.premiBottone();
		Terminale.stampa(l1.eAccesa() == l2.eAccesa());
	}
	
	public static void miPiaccionoILed() {
		Lampadina[][] leds = new Lampadina[6][6];
		for (int i = 0; i < leds.length; i++) {
			for (int j = 0; j < leds[0].length; j++) {
				leds[i][j] = new Lampadina();
			}
		}
		stampa(leds);
		Terminale.stampaLineaVuota();
		for (int i = 0; i < leds.length; i++) {
			leds[i][i].premiBottone();
			leds[i][leds.length - 1 - i].premiBottone();
		}
		stampa(leds);
	}
	
	public static void stampa(Lampadina[][] l) {
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l[0].length; j++) {
				if (l[i][j].eAccesa()) {
					Terminale.stampaNoCR('X');
				} else {
					Terminale.stampaNoCR('O');
				}
			}
			Terminale.stampaLineaVuota();
		}
	}
	
	public void shallowCopy() {
		Lampadina l1 = new Lampadina();
		Lampadina l2 = l1;

		Terminale.stampa(l1);
		Terminale.stampa(l2);
		
		l2.premiBottone();
		
		Terminale.stampa(l1);
		Terminale.stampa(l2);
	}
	
	public void deepCopy() {
		Lampadina l1 = new Lampadina();
		Lampadina l2 = new Lampadina();

		Terminale.stampa(l1);
		Terminale.stampa(l2);
		
		l2.premiBottone();
		
		Terminale.stampa(l1);
		Terminale.stampa(l2);
	}

}
