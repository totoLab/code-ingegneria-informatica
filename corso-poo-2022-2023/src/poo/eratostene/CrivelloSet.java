package poo.eratostene;

import java.util.*;

public class CrivelloSet extends CrivelloAstratto {

	private Set<Integer> crivello = new TreeSet<>();
	private Set<Integer> primi = new TreeSet<>();
	private final int N;
	
	public CrivelloSet(int n) {
		this.N = n;
		for (int i = 2; i <= N; i++) {
			crivello.add(i);
		}
	}
	
	@Override
	public int size() {
		return primi.size();
	}
	
	@Override
	public void filtra() {
		while(!crivello.isEmpty()) {
			int minimo = crivello.iterator().next();
			primi.add(minimo);
			int multiplo = minimo;
			while (multiplo <= N) {
				crivello.remove(multiplo);
				multiplo += minimo;
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return primi.iterator();
	}
	
}
