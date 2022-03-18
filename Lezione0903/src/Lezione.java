import terminale.*;
import java.lang.Math;

public class Lezione {

	public static void main(String[] args) {
		// int[] arrei = {1, 5, 3};
		// gliArrei(arrei);
		
		Lampadina lampadina = new Lampadina();
		Terminale.stampa(lampadina);
		lampadina.premiBottone();
		Terminale.stampa(lampadina);

		Lampadina[] a = new Lampadina[6];
		for (int i = 0; i < a.length; i++) {
			a[i] = new Lampadina();
		}
		
		for (int i = 0; i < a.length; i+=2) {
			a[i].premiBottone();
		}
		
		for (int i = 0; i < a.length; i++) {
			Terminale.stampa(a[i]);
		}
	}
	
	public static void squadra() {
		/* oggetti - DEPRECATED
		Giocatore g1 = new Giocatore();
		
		g1.setNome("vladmir");
		g1.setCognome("putin");
		g1.setAnnoNascita(1956); */
		
		Giocatore g1 = new Giocatore("vladmir", "putin", 1956);
		
		Terminale.stampa("Ciao " + g1.getNome() + " " + g1.getCognome() + " nato nell'anno " + g1.getAnnoNascita() + "."); // old way
		
		Giocatore[] squadra = new Giocatore[3];
		squadra[0] = g1;
		squadra[1] = new Giocatore("Gianluca", "Scamacca", 1999);
		squadra[2] = new Giocatore("Giacomo", "Raspadori", 2000);

		for (Giocatore g : squadra) {
			Terminale.stampa(g); // prints pointer normally, after adding method toString prints default look of object
		}
	}
	
	public static void coseConLeStringhe () {
		String a = "abc";
		String b = "abc";
		
		Terminale.stampa(b.equals(a)); // gli oggetti sono uguali
		Terminale.stampa(a == b); // stesso oggetto
		
		Terminale.stampa(a.indexOf('c')); // first occurrence
		Terminale.stampa(a.lastIndexOf('a'));
		Terminale.stampa(a.substring(1, 2));
		
		Terminale.stampa(a.length());
		
		System.out.printf("%f %f", Math.sqrt(64), Math.PI);
	}
	
	public static void gliArrei(int[] array) {
		Terminale.stampa(array.length);
		int[][] matrix = new int[3][3];
		
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = array;
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Terminale.stampa(matrix[i][j]);
			}
		}
	}

}
