import terminale.Terminale;
import razionali.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
	
		try {
		new Razionale(1,0);
		} catch(EccezioneDenominatoreZero e) {
			// gestione dell'errore
		}
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
	
	public static void testRazionali() {
		ArrayList<Razionale> lista = new ArrayList<>();
		for (int n = 0; n <= 10; n++) {
			lista.add(new Razionale(n, 10));
		}
		Terminale.stampa(lista);
		
		Razionale due = new Razionale(2);
		for (Razionale r : lista) {
			r.moltiplica(due);
		}
		Terminale.stampa(lista);
		
		Razionale mezzo = new Razionale(1, 2);
		for (Razionale r : lista) {
			r.dividi(mezzo);
		}
		
		Terminale.stampa(lista);
	}
	
	public static void ordinamento() {
		ArrayList<Razionale> lista = new ArrayList<>();
		lista.add(new Razionale(1, 2));
		lista.add(new Razionale(1, 3));
		lista.add(new Razionale(1, 4));
		Terminale.stampa(lista);
		
		Collections.sort(lista);
		Terminale.stampa(lista);

	}
	
}
