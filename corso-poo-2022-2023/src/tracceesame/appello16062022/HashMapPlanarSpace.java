package tracceesame.appello16062022;

import java.util.*;

public class HashMapPlanarSpace<T> extends AbstractPlanarSpace<T> {

	private HashMap<T, Tripla<T>> space = new HashMap<>();

	@Override
	public void add(T element, double coordX, double coordY) {
		space.put(element, new Tripla<T>(coordX, coordY, element));
	}

	@Override
	public double getXcoord(T element) {
		return space.get(element).getX();
	}

	@Override
	public double getYcoord(T element) {
		return space.get(element).getY();
	}

	@Override
	public Iterator<T> iterator() {
		return space.keySet().iterator();
	}

	@Override
	public PlanarSpace<T> factory() {
		return new HashMapPlanarSpace<>();
	}
	
	
	
}
