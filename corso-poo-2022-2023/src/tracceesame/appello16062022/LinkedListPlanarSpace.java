package tracceesame.appello16062022;

import java.util.*;

public class LinkedListPlanarSpace<T> extends AbstractPlanarSpace<T> {

	private List<Tripla<T>> space = new LinkedList<>();
	
	@Override
	public void add(T element, double coordX, double coordY) {
		Tripla<T> toAdd = new Tripla<T>(coordX, coordY, element);
		if (!space.contains(toAdd)) {
			space.add(toAdd);
		} else {
			space.remove(toAdd);
			space.add(toAdd);
		}
	}

	@Override
	public double getXcoord(T element) {
		Iterator<Tripla<T>> it = space.iterator();
		while(it.hasNext()) {
			Tripla<T> t = it.next();
			if (t.equals(element)) {
				return t.getX();
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public double getYcoord(T element) {
		Iterator<Tripla<T>> it = space.iterator();
		while(it.hasNext()) {
			Tripla<T> t = it.next();
			if (t.equals(element)) {
				return t.getY();
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public PlanarSpace<T> factory() {
		return new LinkedListPlanarSpace<>();
	}
	
	public Iterator<T> iterator() {
		return new MioIteratore<>(space.iterator());
	}

	private static class MioIteratore<T> implements Iterator<T> {

		private Iterator<Tripla<T>> iterator;
		
		public MioIteratore(Iterator<Tripla<T>> iterator) {
			this.iterator = iterator;
		}
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next().getValore();
		}
		
		@Override
		public void remove() {
			iterator.remove();
		}
		
	}

}
