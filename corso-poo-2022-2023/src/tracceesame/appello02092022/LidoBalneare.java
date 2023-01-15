package tracceesame.appello02092022;

import java.io.Serializable;
import java.util.ArrayList;

class Posizione {
	
	int i, j;
	
	public Posizione(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
}

public interface LidoBalneare extends Serializable {
	
		/* Libera un ombrellone precedentemente occupato*/
		void liberaOmbrellone(Posizione p) throws IllegalStateException;
		
		/*Libera tutte le posizioni*/
		default void liberaTuttigliOmbrelloni() {
			for (Posizione p : ombrelloniOccupati()) liberaOmbrellone(p);
		}
		
		/*Restituisce se un dato ombrellone è occupato o no*/
		boolean eLibero(Posizione p);
		
		/*Restituisce le posizioni degli ombrelloni occupati*/
		default Posizione[] ombrelloniOccupati() {
			ArrayList<Posizione> occupati = new ArrayList<>();
			for (int i = 0; i < numeroFileOmbrelloni(); i++) {
				for (int j = 0; j < numeroOmbrelloniPerFila(); j++) {
					Posizione corrente = new Posizione(i, j);
					if (!eLibero(corrente)) {
						occupati.add(corrente);
					}
				}
			}
			return (Posizione[]) occupati.toArray();
		}
		
		/*Restituisce le posizioni degli ombrelloni liberi*/
		default Posizione[] ombrelloniLiberi() {
			ArrayList<Posizione> liberi = new ArrayList<>();
			for (int i = 0; i < numeroFileOmbrelloni(); i++) {
				for (int j = 0; j < numeroOmbrelloniPerFila(); j++) {
					Posizione corrente = new Posizione(i, j);
					if (!eLibero(corrente)) {
						liberi.add(corrente);
					}
				}
			}
			return (Posizione[]) liberi.toArray();
		}
		
		/*Restituisce il numero di file di ombrelloni nello stabilimento*/
		int numeroFileOmbrelloni();
		
		/*Restituisce il numero di ombrelloni presenti per ogni fila*/
		int numeroOmbrelloniPerFila();	
		
		/*Prenota un ombrellone nella posizione specificate, se la posizione già occupata solleva una eccezione */
		void prenotaOmbrellone(Posizione p) throws IllegalStateException;
		
		/*Prenota un ombrellone in modo random, se nessuna posizione e' libera, solleva una eccezione */
		default Posizione prenotaUnOmbrelloneRandom() throws IllegalStateException {
			Posizione[] liberi = ombrelloniLiberi();
			if (liberi.length == 0) throw new IllegalStateException();
			int index = (int) (Math.random() * liberi.length);
			return liberi[index];
		}

}
