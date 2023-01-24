import terminale.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("abc");
		a.add("ghi");
		a.add("def");
		
		Collections.sort(a);
		Terminale.stampa(a);
		
		ArrayList<Giocatore> squadra = new ArrayList<>();
		squadra.add(new Giocatore("Mario", "Rossi", 2000)); 
		squadra.add(new Giocatore("Luca", "Bianchi", 2002));
		squadra.add(new Giocatore("Giovanni", "Verdi", 1995));
		
		Collections.sort(a);
		Terminale.stampa(a);
	}

}
