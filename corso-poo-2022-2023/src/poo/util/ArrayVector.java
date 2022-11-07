package poo.util;

public class ArrayVector extends AbstractVector {
	
	private Object[] array;
	
	public ArrayVector(int capacita) {
		super();
		array = new Object[capacita];
	}
	
	private void espandi() { // raddoppia array
		
	}
	
	private void contrai() { // dimezza array
		
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
	protected Vector newInstanceVector() {
		return new ArrayVector(100);
	}

}
