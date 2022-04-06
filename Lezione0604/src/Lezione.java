import terminale.Terminale;

public class Lezione {

	public static void main(String[] args) {
		Automa a = new Automa();
		String s = "abbbbcc"; // pattern: 1 a, almeno 1 b, 2 c. Con altri pattern non viene riconosciuta.
		boolean riconosciuta = a.riconosci(s);
		if (riconosciuta) {
			Terminale.stampa("Stringa " + s + " riconosciuta");
		} else {
			Terminale.stampa("Stringa " + s + " non riconosciuta");
		}
	}

}
