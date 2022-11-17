package poo.agendina;

import java.util.Iterator;

public abstract class AgendinaAbstract implements Agendina {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Nominativo n : this) {
			sb.append(n + "\n");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Agendina)) return false;
		if (o == this) return true;
		Agendina a = (Agendina) o;
		if (a.size() != this.size()) return false;
		Iterator<Nominativo> i1 = this.iterator();
		Iterator<Nominativo> i2 = a.iterator();
		while(i1.hasNext()) {
			Nominativo n1 = i1.next();
			Nominativo n2 = i2.next();
			if (!n1.equals(n2)) return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int M = 43;
		int h = 0;
		for (Nominativo n : this) {
			h = h * M + n.hashCode();
		}
		return h;
	}
}
