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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector subVector(int da, int a) {
		if (!(da >= 0 && a < this.size() && da <= a)) {
			throw new IndexOutOfBoundsException();
		}
		
		Vector v = new ArrayVector(a - da);
		for (int i = da; i < a; i++) {
			v.add(this.get(i));
		}
		return v;
	}

}
