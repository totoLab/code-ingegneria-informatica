import terminale.*;
import java.util.*;
public class Lezione {

	public static void main(String[] args) {
		wrapperClasses();
	}
	
	public static void instances() {
		Lampadina l1 = new Lampadina();
		Lampadina l2 = new Lampadina();
		
		Terminale.stampa(l1 == l2);
		Terminale.stampa(l1 instanceof Lampadina);
		Terminale.stampa(l2 instanceof Lampadina);
		// Terminale.stampa(l1 instanceof String); // impossible to execute

	}
	
	public static void nullPointers () {
		Lampadina l1 = null; // null pointer, in case we don't yet have a pointer to assign to the object
	}
	
	public static void objectComparisons() {
		Lampadina l1 = new Lampadina();
		Lampadina l2 = new Lampadina();
		
		Terminale.stampa("Confronto per identità: " + (l1 == l2));
		Terminale.stampa("Confronto per stato: " + l1.equals(l2));
		
		l1.premiBottone();
		Terminale.stampa("Confronto per identità: " + (l1 == l2));
		Terminale.stampa("Confronto per stato: " + l1.equals(l2));
	}
	
	public static void playersComparison() {
		Giocatore g1 = new Giocatore("Mario", "Rossi", 2002);
		Giocatore g2 = new Giocatore("Giovanni", "Calogero", 2000);
		Terminale.stampa("Confronto per identità: " + (g1 == g2));
		Terminale.stampa("Confronto per stato: " + g1.equals(g2));
	}
	
	public static void arraysMaBelli() {
		ArrayList<Giocatore> lista = new ArrayList<Giocatore>(); // alternative syntax: ArrayList<Giocatore> lista = new ArrayList<>();
		
		Terminale.stampa(lista.isEmpty());
		
		Giocatore g1 = new Giocatore("Mario", "Rossi", 2000);
		Giocatore g2 = new Giocatore("Giovanni", "Calogero", 2004);
		lista.add(g1);
		lista.add(g2);
		
		Terminale.stampa(lista.size());
		
		/* 
		lista.clear(); // empties the array
		Terminale.stampa(lista.size()); // 0
		*/
		
		Terminale.stampa(lista); // Giocatore has a method toString that's invoked to print the object
		
		Giocatore g3 = new Giocatore("Giovanni", "Calogero", 2004); // equal to g2
		Terminale.stampa(lista.contains(g3)); // contains an object equal to g3, it called g#.equals(g3)
		
		Terminale.stampa(lista.get(0)); // lista[0] doesn't work
		
		// remove from array methods
		lista.remove(0); // remove element at index
		Terminale.stampa(lista);
		lista.add(g1); // re-add g1
		Terminale.stampa(lista);
		lista.remove(g3); // alternative to remove the first element g#.equals(g3)
		Terminale.stampa(lista);
		lista.add(g2); // re-add g2
		Terminale.stampa(lista);
		
		Terminale.stampa(lista.indexOf(g3)); // first element g#.equals(g3) in the array
		
		Giocatore g4 = new Giocatore("Paolo", "Verdi", 2001);
		lista.set(0, g4); // substitute element at index 0 with second argument (g4)
		Terminale.stampa(lista);
		
	}
	
	public static void wrapperClasses() {
		// ArrayList<int> lista = new ArrayList<>(); // impossible because "int" is not an object
		ArrayList<Integer> numbers = new ArrayList<>(); // there is a wrapper class for every primitive type
		
		Integer i = 5; // alternative but deprecated syntax: Integer i = new Integer(5);
		numbers.add(i);
		Terminale.stampa(numbers);
		
		int j = 10;
		// auto-boxing
		numbers.add(j); // godo è troppo comoda sta cosa, okay scusate torno all'inglese. all right
		Terminale.stampa(numbers);
		
		int x = numbers.get(0); // auto-unboxing: takes elements from wrapper class and assign the value to a primitive type variable
	}
	
}
