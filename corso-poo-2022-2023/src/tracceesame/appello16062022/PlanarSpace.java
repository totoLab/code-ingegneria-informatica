package tracceesame.appello16062022;

import java.util.Iterator;

public interface PlanarSpace<T> extends Iterable<T> {
	
	/* aggiunge l'elemento elem con coordinate coordX e coordY */
	/* se l'elemento e' presente ne aggiorna la posizione*/
	void add(T element, double coordX, double coordY);
	
	/* restituisce true se element e' contenuto nello spazio */
	default boolean contains(T element) {
		Iterator<T> it = this.iterator();
		while(it.hasNext()) {
			T corrente = it.next();
			if (corrente.equals(element)) return true;
		}
		return false;
	}
	
	/* rimuove element dalla struttura restituendo true, restituisce false in caso contrario */
	default boolean remove(T element) {
		Iterator<T> it = this.iterator();
		while(it.hasNext()) {
			T corrente = it.next();
			if (corrente.equals(element)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	/* Restituisce un nuovo PlanarSpace formato dagli elementi le cui      
       coordinate soddisfano i vincoli delle posizioni passate come parametri */
	PlanarSpace<T> get(double xMin, double xMax, double yMin, double yMax);
	
	/* restituisce la coordinata x di elment */
	double getXcoord(T element);
	
	/* restituisce la coordinata y di elment */
	double getYcoord(T element);
	
}