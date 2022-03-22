import terminale.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(5);
		a.add(4);
		a.add(1);
		a.add(7);
		a.add(4);

		ArrayList<Integer> b = new ArrayList<>(a);
		
		Terminale.stampa(a);
		Terminale.stampa(b);
		
		//metodiListe(a);
		//garbageCollection();
		//metodiArrayClassici();
	}
	
	public static void metodiListe(ArrayList<Integer> a) {
		// Java Collections Framework (JCF) è contenuto in java.util
		// contiene metodi relativi alle collezioni/insiemi di oggetti
		
		// min e max
		int m = Collections.max(a);
		Terminale.stampa(m);
		m = Collections.min(a);
		Terminale.stampa(m);
		
		// quante volte è contenuto un elemento
		Terminale.stampa(Collections.frequency(a, 4)); // 2
		
		// mischia casualmente
		Terminale.stampa(a);
		Collections.shuffle(a);
		Terminale.stampa(a);
		
		// inverte l'array
		Terminale.stampa(a);
		Collections.reverse(a);
		Terminale.stampa(a);
		
		// ordinamento (crescente)
		Terminale.stampa(a);
		Collections.sort(a);
		Terminale.stampa(a);
		
		// ricerca binaria
		Terminale.stampa(Collections.binarySearch(a, 5)); // restituisce l'indice
	}
	
	public static void garbageCollection() {
		ArrayList<Integer> a = new ArrayList<>(); // creiamo l'array in memoria e assegnamo alla variabile il puntatore
		a.add(5);
		a.add(4);
		a.add(1);
		a.add(7);
		a.add(4);

		a = null; // esiste ancora l'array in memoria ma nessun puntatore è collegato a quell'array
		Terminale.stampa(a); // stampa il puntatore nullo
		Terminale.stampa(a.get(0)); // ma non sa cosa fare con .get() perché l'array non ha più nome
		
		// l'ArrayList in memoria non serve più e java si occuperà di eliminarlo con la GARBAGE COLLECTION in automatico (best effort)
		
		// in caso possiamo invocare il garbage collector esplicitamente come parte del programma
		System.gc();
	}
	
	public static void metodiArrayClassici() {
		int[] a = new int[100];
		/* metodo classico
		for (int i = 0; i < 100; i++) {
			array[i] = 10;
		} */
		//
		Arrays.fill(a, 10);
		
		// Terminale.stampa(a); ci da il puntatore
		String arrayComeStringa = Arrays.toString(a); // questo metodo ce lo restituisce come stringa pronta da stampare
		Terminale.stampa(arrayComeStringa);
		Arrays.sort(a);
		// ecc...
	}
	

}
