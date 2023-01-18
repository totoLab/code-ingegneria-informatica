package tracceesame.appello03022022;

import java.util.*;
public class CollezioneImpl<T extends Comparable<? super T>> extends AbstractCollezioneOrdinata<T> {

	protected Map<T, Integer> collection = new TreeMap<>();
	
	@Override
	public void add(int n, T elem) {
		if (collection.containsKey(elem)) {
			collection.put(elem, n);
		} else {
			collection.put(elem, collection.get(elem) + n);
		}
	}

	@Override
	public int occorrenze(T elem) {
		if (collection.containsKey(elem)) {
			return collection.get(elem);
		}
		return 0;
	}
	
	@Override
	public void remove(int n, T elem) {
		if (collection.containsKey(elem)) {
			int newNoOfOccurrences = collection.get(elem) - n;
			if (newNoOfOccurrences > 0) {
				collection.put(elem, newNoOfOccurrences);
			} else {
				collection.remove(elem);
			}
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return collection.keySet().iterator();
	}

	
}
