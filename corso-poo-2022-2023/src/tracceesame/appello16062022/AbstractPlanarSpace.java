package tracceesame.appello16062022;

import java.util.Iterator;

abstract class AbstractPlanarSpace<T> implements PlanarSpace<T> {

	@Override
	public int hashCode() {
		int prime = 431;
		int h = 0;
		Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			T element = it.next();
			h = h + prime * element.hashCode();
		}
		return h;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof PlanarSpace)) return false;
		
		PlanarSpace<T> p = (PlanarSpace<T>) o;
		Iterator<T> it1 = this.iterator();
		Iterator<T> it2 = p.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			T element1 = it1.next();
			T element2 = it2.next();
			if (!element1.equals(element2)) return false;
		}
		return !(it1.hasNext() || it2.hasNext()); // se entrambi non hanno un successivo sono uguali altrimenti no
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			T element = it.next();
			sb.append(element);
			sb.append(",\n");
		}
		return sb.toString();
	}
	
	abstract public PlanarSpace<T> factory();
	
	@Override
	public PlanarSpace<T> get(double xMin, double xMax, double yMin, double yMax) {
		PlanarSpace<T> ret = factory();
		Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			T element = it.next();
			double x = getXcoord(element);
			double y = getYcoord(element);
			if (xMin <= x && x <= xMax &&
					yMin <= y && y <= yMax
			) {
				ret.add(element, x, y);
			}
		}
		return ret;
	}
}
