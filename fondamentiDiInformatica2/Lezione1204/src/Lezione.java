import listeconcatenate.*;
import terminale.*;

public class Lezione {

	public static void main(String[] args) {
		ListaConcatenataInt lista = new ListaConcatenataInt();
		lista.aggiungiInCoda(10);
		lista.aggiungiInCoda(20);
		lista.aggiungiInCoda(30);
		lista.aggiungiInCoda(100);
		Terminale.stampa(lista);
		
		testListaVuota(lista);
		Terminale.stampa(lista.listaInvertita());
		
	}
	
	public static void testListaVuota(ListaConcatenataInt l) {
		while (true) {
			l.rimuoviTesta();
			Terminale.stampa(l);
		}
	}

}
