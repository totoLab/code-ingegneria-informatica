package poo.util;

public interface Vector<T> extends Iterable<T> { // non ordinata, ammette duplicati
	
	/**
	 * restituisce il numero di elementi presente nella struttura dati
	 * @return numero di elementi
	 */
	int size();
	
	/**
	 * restituisce la posizione della prima occorrenza di el all'interno della struttura dati
	 * @param el l'elemento di cui si vuole conoscere la posizione
	 * @return posizione della prima occorrenza di el, -1 se l'elemento non esiste
	 */
	int indexOf(T el);

	boolean contains(T el);
	
	/**
	 * restituisce l'oggetto presente nella struttura dati all'indice index
	 * @param index
	 * @return l'oggetto all'indice index
	 * @throws IndexOutOfBoundsException nel caso in cui index non sia valido
	 */
	T get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * sostituisce el in posizione index
	 * @param index indica la posizione in cui sostituire l'oggetto
	 * @param el oggetto che viene inserito nella struttura dati
	 * @return oggetto precedentemenete presente in posizione index
	 * @throws IndexOutOfBoundsException nel caso in cui index non sia valido
	 */
	T set(int index, T el) throws IndexOutOfBoundsException;
	
	void add(T el);  // add last
	
	void add(int index, T el) throws IndexOutOfBoundsException;
	
	void remove(T el); // first occurrence
	
	T remove(int index);
	
	void clear();
	
	boolean isEmpty();
	
	Vector<T> subVector(int from, int to);
}
