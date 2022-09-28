import terminale.*;
import java.util.*;

public class Lezione {

	public static void main(String[] args) {
		
		//esempioSquadraJuventus();
		
		esempioSquadraReggina();
	}
	
	
	
	public static void arrayPiuListe() {
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
	
	public static void banale() {
		int eta = Terminale.richiediInt("Inserisci l'età");
		/*
		String risultato = null;
		if (eta >= 18) {
			risultato = "Maggiorenne";
		} else {
			risultato = "Minorenne";
		}
		*/
		
		// CONDITIONAL EXPRESSION [condizione] ? [valore se condizione vera] : [valore se condizione falsa];
		String risultato = eta >= 18 ? "Maggiorenne" : "Minorenne";
		
		Terminale.stampa(risultato);
	}
	
	public static void esempioSquadraJuventus() {
		Squadra juventus = new Squadra("Juventus");
		
		Giocatore dybala = new Giocatore("Paulo", "Dybala", 1993);
		Giocatore deLigt = new Giocatore("Matthijs", "De Ligt", 1999);
		
		juventus.aggiungiGiocatore(dybala);
		juventus.aggiungiGiocatore(deLigt);
		
		// juventus.rimuoviGiocatorePosizione(1);
		// juventus.rimuoviGiocatorePosizione(0);
		
		ArrayList<Giocatore> a = juventus.getGiocatori();
		a.remove(0); // non rimuove il giocatore nell'oggetto originale
		Terminale.stampa(a);
		Terminale.stampa(juventus);
		
		juventus.getGiocatorePosizione(0);
		
		Terminale.stampa(juventus);
		Giocatore chiellini = new Giocatore("Giorgio", "Chiellini", 1984);
		juventus.sostituisciGiocatorePosizione(chiellini, 1);
		Terminale.stampa(juventus);
		
		Terminale.stampa(juventus.contiene(dybala));
	}
	
	
	public static void esempioSquadraReggina() {
		Squadra reggina = new Squadra("Reggina 1914");
		
		Giocatore turati = new Giocatore("Alessandro", "Micai", 1993);
		Giocatore cionek = new Giocatore("Thiago", "Cionek", 1986);
		Giocatore amione = new Giocatore("Bruno", "Amione", 2002);
		Giocatore diChiara = new Giocatore("Gianluca", "Di Chiara", 1993);
		Giocatore giraudo = new Giocatore("Federico", "Giraudo", 1998);
		Giocatore perparim = new Giocatore("Perparim", "Hetemaj", 1986);
		Giocatore crisetig = new Giocatore("Lorenzo", "Crisetig", 1993);
		Giocatore cortinovis = new Giocatore("Alessandro", "Cortinovis", 2001);
		Giocatore kupisz = new Giocatore("Tomasz", "Kupisz", 1990);
		Giocatore folorunsho = new Giocatore("Michael", "Folorunsho", 1998);
		Giocatore rivas = new Giocatore("Rigoberto", "Rivas", 1998);
		
		Giocatore[] giocatori = {turati, cionek, amione, diChiara, giraudo, perparim, crisetig, cortinovis, kupisz, folorunsho, rivas};
		for (Giocatore g : giocatori) {
			reggina.aggiungiGiocatore(g);
		}
		Terminale.stampa(reggina);
	}
	
}
