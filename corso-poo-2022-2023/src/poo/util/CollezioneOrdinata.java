package poo.util;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T>{

	int size();
	void clear();
	boolean contains(T x); 
	
	void add(T x);
	void remove(T x);
	
	T get(T x);
	boolean isEmpty();
	boolean isFull();
}
