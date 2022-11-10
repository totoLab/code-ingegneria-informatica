package poo.util;

import java.util.*;

public class ArrayVector<T> extends AbstractVector<T> {
	
	private T[] array;
	public int contatoreDelleModifiche = 0;
	
	public ArrayVector(int capacita) {
		super();
		if (capacita < 0) throw new IllegalArgumentException();
		array = (T[]) new Object[capacita];
	}
	
	public ArrayVector(Vector<T> v) {
		array = (T[]) new Object[v.size()];
		for (int i = 0; i < this.size(); i++) {
			this.add( v.get(i));
		}
	}
	
	private void espandi() { // raddoppia array
		T[] arrayDoppio = (T[]) new Object[array.length * 2];
		System.arraycopy(array, 0, arrayDoppio, 0, size());
		array = arrayDoppio;
	}
	
	private void contrai() { // dimezza array
		T[] arrayMezzo = (T[]) new Object[array.length / 2];
		System.arraycopy(array, 0, arrayMezzo, 0, size() / 2);
		array = arrayMezzo;
	}
	
	@Override
	public int indexOf(Object el) {
		for (int i = 0; i < size(); i++) {
			if (array[i].equals(el)) return i;
		}
		return -1;
	}

	@Override
	public T get(int index) {
		return array[index];
	}

	@Override
	public T set(int index, Object el) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (el == null) {
			throw new IllegalArgumentException();
		}
			
		T old = array[index];
		array[index] = (T) el;
		return old;
	}

	@Override
	public void add(int index, Object el) {
		//checks
		
		if (size() == array.length) espandi();
		for (int i = size() - 1; i >= index; i--) {
			array[i + 1] = array[i];
		}
		array[index] = (T) el;
		size++;

	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clear() {
		array = (T[]) new Object[array.length];
		size = 0;
	}
	
	@Override
	protected Vector<T> newInstanceVector(int size) {
		return new ArrayVector<T>(size);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MioIteratore();
	}
	
	private class MioIteratore implements Iterator<T> { // ogni istanza di MioIteratore è legata a una e una sola instanza di ArrayVector
		
		private int index = 0;
		private boolean removable = false;
		private final int mioContatore = ArrayVector.this.contatoreDelleModifiche ;
		
		@Override
		public boolean hasNext() {
			if (index < size()) {
				return true;
			}
			return false;
		}
		
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T toReturn = array[index];
			index++;
			removable = true;
			return toReturn;
		}
		
		@Override
		public void remove() {
			if (!removable)
				throw new IllegalStateException();
			removable = false;
			for (int i = index; i < size(); i++) {
				array[i - 1] = array[i];
			}
			size--;
		}
		
	}

}
