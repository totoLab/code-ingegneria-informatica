package tracceesame.appello03022022;

import java.util.Iterator;

public abstract class AbstractCollezioneOrdinata<T extends Comparable<? super T>> implements CollezioneConDuplicati<T> {
	
	@Override
	public int hashCode() {
		int prime = 31;
		int h = 0;
		for (T element : this) {
			h = h + prime * element.hashCode();
		}
		return h;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof CollezioneConDuplicati)) return false;
		
		CollezioneConDuplicati<T> c = (CollezioneConDuplicati<T>) o;

		Iterator<T> it1 = this.iterator();
		Iterator<T> it2 = c.iterator();
		while(it1.hasNext() && it2.hasNext()) {
			if (!it1.next().equals(it2.next())) return false;
		}
		return it1.hasNext() || it2.hasNext(); // if after finishing one of the lists the other has still elements they are not equal
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T element : this) {
			sb.append(element.toString());
			sb.append(", ");
		}
		return sb.toString();
	}
	
}
