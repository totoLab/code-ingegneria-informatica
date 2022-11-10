package poo.util;

public abstract class AbstractVector<T> implements Vector<T> {
	
	protected int size;
	
	public AbstractVector() {
		this.size = 0;
	}
		
	public int size() {
		return size;
	}
	
	@Override
	public boolean contains(T el) {
		return indexOf(el) != -1;
	}
	
	abstract public T get(int index);
	
	abstract public T set(int index, T el);
	
	@Override
	public void add(T el) {
		add(size(), el);
	}
	
	abstract public void add(int index, T elem);

	@Override
	public void remove(T el) {
		int i = indexOf(el);
		if (i != -1) remove(i);
	}
	
	abstract public T remove(int index);
	
	@Override
	public void clear() {
		while (size != 0) {
			remove(0);
		}
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	abstract protected Vector<T> newInstanceVector(int size); // factory
	
	@Override
	public Vector<T> subVector(int da, int a) {
		if (!(da >= 0 && a < this.size() && da <= a)) {
			throw new IndexOutOfBoundsException();
		}
		
		Vector<T> v = newInstanceVector(a - da); 
		for (int i = da; i < a; i++) {
			v.add(this.get(i));
		}
		return v;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(size * 3);
		sb.append("[");
		for (int i = 0; i < this.size(); i++) {
			sb.append(this.get(i));
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int MOL = 41;
		int h = 0;
		for (int i = 0; i < this.size(); i++) {
			h = h * MOL + this.get(i).hashCode();
		}
		return h;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vector)) return false;
		
		Vector<T> v = (Vector<T>) o;
		if (this.size() == v.size()) return false;
		for (int i = 0; i < this.size(); i++) {
			if (!this.get(i).equals(v.get(i))) return false;
		}
		return true;
	}
}
