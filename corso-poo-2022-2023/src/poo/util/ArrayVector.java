package poo.util;

import java.util.*;

public class ArrayVector extends AbstractVector {
	
	private Object[] array;
	
	public ArrayVector(int capacita) {
		super();
		array = new Object[capacita];
	}
	
	private void espandi() { // raddoppia array
		Object[] arrayDoppio = new Object[array.length * 2];
		System.arraycopy(array, 0, arrayDoppio, 0, size());
		array = arrayDoppio;
	}
	
	private void contrai() { // dimezza array
		Object[] arrayMezzo = new Object[array.length / 2];
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
	public Object get(int index) {
		return array[index];
	}

	@Override
	public Object set(int index, Object el) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (el == null) {
			throw new IllegalArgumentException();
		}
			
		Object old = array[index];
		array[index] = el;
		return old;
	}

	@Override
	public void add(int index, Object el) {
		//checks
		
		if (size() == array.length) espandi();
		for (int i = size() - 1; i >= index; i--) {
			array[i + 1] = array[i];
		}
		array[index] = el;
		size++;

	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Vector newInstanceVector(int size) {
		return new ArrayVector(size);
	}
	
	@Override
	public Iterator iterator() {
		return new MioIteratore();
	}
	
	private class MioIteratore implements Iterator { // ogni istanza di MioIteratore è legata a una e una sola instanza di ArrayVector
		
		private int index = 0;
		private boolean removable = false;
		
		@Override
		public boolean hasNext() {
			if (index < size()) {
				return true;
			}
			return false;
		}
		
		@Override
		public Object next() {
			if (!hasNext()) throw new NoSuchElementException();
			Object toReturn = array[index];
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
