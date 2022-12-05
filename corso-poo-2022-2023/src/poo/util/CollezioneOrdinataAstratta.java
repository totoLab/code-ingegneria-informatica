package poo.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CollezioneOrdinata)) return false;
		if (o == this) return true;
		
		@SuppressWarnings("rawtypes")
		CollezioneOrdinata a = (CollezioneOrdinata) o;
		
		if (a.size() != this.size()) return false;
		Iterator<T> i1 = this.iterator();
		
		@SuppressWarnings("rawtypes")
		Iterator i2 = a.iterator();
		
		while(i1.hasNext()) {
			T n1 = i1.next();
			Object n2 = i2.next();
			if (!n1.equals(n2)) return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int M = 43;
		int h = 0;
		for (T x : this) {
			h = h + M * x.hashCode();
		}
		return h;
	}
}
