package poo.util;

public abstract class AbstractVector implements Vector {
	
	protected int size;
	
	public AbstractVector() {
		this.size = 0;
	}
		
	public int size() {
		return size;
	}
	
	@Override
	public boolean contains(Object el) {
		return indexOf(el) != -1;
	}
	
	abstract public Object get(int index);
	
	abstract public Object set(int index, Object el);
	
	@Override
	public void add(Object el) {
		add(size(), el);
	}
	
	abstract public void add(int index, Object elem);

	@Override
	public void remove(Object el) {
		int i = indexOf(el);
		if (i != -1) remove(i);
	}
	
	abstract public Object remove(int index);
	
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
	
	abstract public Vector subVector(int da, int a);
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
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
		
		Vector v = (Vector) o;
		if (this.size() == v.size()) return false;
		for (int i = 0; i < this.size(); i++) {
			if (!this.get(i).equals(v.get(i))) return false;
		}
		return true;
	}
}
