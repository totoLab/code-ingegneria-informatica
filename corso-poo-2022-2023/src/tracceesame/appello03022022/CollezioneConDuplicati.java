package tracceesame.appello03022022;

import java.util.*;

public interface CollezioneConDuplicati<T extends Comparable<? super T>> extends Iterable<T> {
	/*
	 * Aggiunge 'n' occorrenze di 'elem' nella collezione  
	 */
	void add(int n, T elem);
	
	/*
	 * Restituisce il numero di occorrenze di 'elem'
	 */
	default int occorrenze(T elem) {
		Iterator<T> it = this.iterator();
		int cont = 0;
		while (it.hasNext()) {
			T element = it.next();
			if (elem.equals(element)) cont++;
		}
		return cont;
	}
	
	/*
	 * Ricerca e restituisce l'elemento 'elem' nella collezione.
	 * L'eccezione è sollevata se l'elemento non esiste
	 */
	default T get(T elem) throws NoSuchElementException {
		Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			T element = it.next();
			if (elem.equals(element)) return element;
		}
		throw new NoSuchElementException();
	}
	
	/*
	 * Rimuove 'n' istanze di 'elem' dalla collezione.
	 * Se 'n' è maggiore di 'occorrenze(elem)' le istanze vengono
	 * eliminate tutte
	 */
	default void remove(int n, T elem) {
		Iterator<T> it = this.iterator();
		int deleted = 0;
		while (deleted < n && it.hasNext()) {
			T element = it.next();
			if (elem.equals(element)) {
				it.remove();
			}
		}
	}
}