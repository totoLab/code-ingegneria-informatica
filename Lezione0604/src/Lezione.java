import terminale.Terminale;
import razionali.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
		ArrayList<Razionale> lista = new ArrayList<>();
		for (int n = 0; n <= 10; n++) {
			lista.add(new Razionale(n, 10));
		}
		Terminale.stampa(lista);
	}

	
	public static void automa() {
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
