package listeconcatenate;
import terminale.*;

public class Lezione {

	public static void main(String[] args) {
		ListaConcatenataInt lista = new ListaConcatenataInt();
		lista.aggiungiInCoda(10);
		lista.aggiungiInCoda(20);
		lista.aggiungiInCoda(30);
		lista.aggiungiInCoda(100);
		
		while (!lista.eVuota()) {
			lista.rimuoviCoda();
			Terminale.stampa(lista);
		}
	}
}
