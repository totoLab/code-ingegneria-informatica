package tracceesame.appello16062022;

import java.util.Iterator;

public interface PlanarSpace<T> extends Iterable<T> {

	void add(T element, double coordX, double coordY);
	
	default boolean contains(T element) {
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T t = it.next();
			if (t.equals(element)) return true;
		}
		return false;
	}
	
	default boolean remove(T element) {
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T t = it.next();
			if (t.equals(element)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	PlanarSpace<T> get(double xMin, double xMax, double yMin, double yMax);
	
	double getXcoord(T element);
	double getYcoord(T element);
	
}
