import terminale.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
		
	}
	
	public static void metodiLinkedLists() {
		LinkedList<Integer> lista = new LinkedList<>();
		
		for (int n = 1; n<= 10; n++) {
			lista.addLast(n); // add() é uguale ad addLast()
		}
		
		lista.clear(); // svuota
		
		for (int n = 1; n<= 10; n++) { // lista al contrario
			lista.addFirst(n);
		}
		
		Terminale.stampa(lista.contains(5));
		
		Terminale.stampa(lista.removeFirst());
		Terminale.stampa(lista);
		
		Terminale.stampa(lista.removeLast());
		Terminale.stampa(lista);

		Terminale.stampa(lista.getFirst());
		Terminale.stampa(lista.getLast());
		
		for (int i = 0; i < 10; i++) {
			Terminale.stampa(lista.get(i)); // get() riparte sempre dal primo elemento per arrivare all'indice
		}	
	}
	
	public static void iteratorssss () {
		LinkedList<Integer> lista = new LinkedList<>();
		
		for (int n = 1; n<= 10; n++) {
			lista.addLast(n);
		}
		
		Terminale.stampa(lista);
		
		// iterare senza accesso diretto come se fosse un normale array
		ListIterator<Integer> iteratore = lista.listIterator(); // parte dalla posizione -1
		
		int primo = iteratore.next(); // prossimo elemento dell'iteratore, il primo
		Terminale.stampa(primo);
		
		int secondo = iteratore.next(); // prossimo elemento dell'iteratore, il primo
		Terminale.stampa(secondo);
		
		Terminale.stampa(iteratore.previous());
		
		Terminale.stampa(iteratore.previous());
		
		iteratore = lista.listIterator(8); // possiamo partire da un altro indice
		Terminale.stampa(iteratore.next());
		
		// quando arriva all'ultimo elemento e chiediamo il prossimo da errore, quindi controlliamo prima
		if (iteratore.hasNext()) {
			Terminale.stampa("ce l'ho! è " + iteratore.next());
		}
		
		// iterazione completa standard
		iteratore = lista.listIterator();
		while (iteratore.hasNext()) {
			Terminale.stampa(iteratore.next());
		}
		// anche all'indietro
		while (iteratore.hasPrevious()) {
			Terminale.stampa(iteratore.previous());
		}
	}
	
	public static void notaSugliStati() {
		LinkedList<Integer> lista = new LinkedList<>();
		
		for (int n = 1; n <= 3; n++) {
			lista.addLast(n);
		}
		
		Terminale.stampa(lista);
		
		ListIterator<Integer> iteratore = lista.listIterator();
		
		iteratore.next();
		iteratore.next();
		iteratore.remove(); // rimuove l'ultimo nodo attraversato dall'iteratore, quindi il 2
		
		Terminale.stampa(lista);
		
		iteratore = lista.listIterator(); // reset
		iteratore.next(); // tra 1 e 2
		iteratore.next(); // tra 2 e 3
		iteratore.next(); // tra 3 e 4
		iteratore.previous(); // tra 2 e 3
		iteratore.remove(); // rimuove l'ultimo nodo attraversato dall'iteratore, quindi il 3
		
		iteratore = lista.listIterator(); // reset
		iteratore.next(); // tra 1 e 2
		iteratore.next(); // tra 2 e 3
		iteratore.next(); // tra 3 e 4
		iteratore.add(10); // aggiunge l'elemento nel punto in cui si trova
		Terminale.stampa(lista);
		
		iteratore = lista.listIterator(); // reset
		iteratore.next(); // tra 1 e 2
		iteratore.next(); // tra 2 e 3
		iteratore.next(); // tra 3 e 4
		iteratore.set(10); // sostituisce l'ultimo nodo attraversato
		Terminale.stampa(lista);
	}

}
