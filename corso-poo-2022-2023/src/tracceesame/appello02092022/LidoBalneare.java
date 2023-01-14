package tracceesame.appello02092022;

import java.io.Serializable;

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
		Posizione[] ombrelloniOccupati();
		
		/*Restituisce le posizioni degli ombrelloni liberi*/
		Posizione[] ombrelloniLiberi();
		
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
