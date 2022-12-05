package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaOrdinataConcatenata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	
	private static class Nodo<E> {
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> testa = null;
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		testa = null;
		size = 0;
	}

	@Override
	public boolean contains(T x) {
		Nodo<T> corr = testa;
		for ( ; corr != null ; ) {
			if (corr.info.equals(x)) return true;
			if (corr.info.compareTo(x) > 0) return false;
			corr = corr.next;
		}
		return false;
	}

	@Override
	public void add(T x) {
		Nodo<T> nuovo = new Nodo<>();
		nuovo.info = x;
		
		if (testa == null || testa.info.compareTo(x) > 0) {
			nuovo.next = testa;
			testa = nuovo;
		} else {
			Nodo<T> pre = testa, corr = testa.next;
			while (corr != null || corr.info.compareTo(x) < 0) {
				pre = corr;
				corr = corr.next;
			}
			pre.next = nuovo;
			nuovo.next = corr;
		}
		size++;
	}

	@Override
	public void remove(T x) {
		Nodo<T> pre = null;
		Nodo<T> corr = testa;
		while( corr != null && corr.info.compareTo(x) < 0) {
			pre = corr;
			corr = corr.next;
			
		}
		if (corr != null && corr.info.equals(x)) {
			if (corr == testa) {
				testa = testa.next;
			} else {
				pre.next = corr.next;
			}
			size--;
		}
	}

	@Override
	public T get(T x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyIterator implements Iterator<T> {

		Nodo<T> pre = null, corr = null;
		
		@Override
		public boolean hasNext() {
			if (corr == null) {
				return testa != null;
			}
			return corr.next != null;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (corr == null) {
				corr = testa;
			} else {
				pre = corr;
				corr = corr.next;
			}
			return corr.info;
		}
		
	}
}
