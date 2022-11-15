package esercitazioni.matrici;

public abstract class MatriceQuadrataAbstract implements MatriceQuadrata {
    
	protected int ordine;
	
	public MatriceQuadrataAbstract(int ordine) {
		if (ordine < 0) throw new IllegalArgumentException();
		this.ordine = ordine;
	}
	
	public int getOrdine() {
		return this.ordine;
	}
	
	public boolean validBounds(int row, int col) {
		return row < ordine && col < ordine;
	}
	
	public boolean compatibili(MatriceQuadrata m1, MatriceQuadrata m2) {
		return m1.getOrdine() == m2.getOrdine();
	}

	public abstract int getEl(int row, int col) throws IndexOutOfBoundsException;
	
	public abstract void setEl(int row, int col, int newEl) throws IndexOutOfBoundsException;
	
	public void addWithThis(MatriceQuadrata m) {
		if (!compatibili(this, m)) throw new IllegalArgumentException();
		int ordine = m.getOrdine();
		for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						 i, j, this.getEl(i, j) + m.getEl(i, j)
						);
			}
		}
	}
	
    public void mulNumWithThis(int num) {
    	int ordine = this.getOrdine();
    	for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						i, j, this.getEl(i, j) * num
						);
			}
    	}
    }
    
    public void mulWithThis(MatriceQuadrata m) {
		if (!compatibili(this, m)) throw new IllegalArgumentException();
    	int ordine = this.getOrdine();
    	for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				int ps = 0;
				for (int k = 0; k < ordine; k++) {
					ps += this.getEl(i, k) * m.getEl(k, j);
				}
				this.setEl(i, j, ps);
			}	
		}
    }
        
	public abstract MatriceQuadrata newInstanceMatriceQuadrata(int ordine);
    
    @Override
	public MatriceQuadrata add(MatriceQuadrata m) {
		MatriceQuadrata result = newInstanceMatriceQuadrata(this);
		
		result.addWithThis(m);
		return result;
	}

	@Override
	public MatriceQuadrata mul(int num) {
		MatriceQuadrata result = newInstanceMatriceQuadrata(this);
		
		result.mulNumWithThis(num);
		return result;
	}

	
	@Override
	public MatriceQuadrata mul(MatriceQuadrata m) {
		MatriceQuadrata result = newInstanceMatriceQuadrata(this);
		
		result.mulWithThis(m);;
		return result;
	}

	public boolean puntoDiSella() {
		// TODO
	}
    
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		int ordine = this.getOrdine();
		sb.append("[\n");
		for (int i = 0; i < ordine; i++) {
			sb.append("    [");
			for (int j = 0; j < ordine; j++) {
				sb.append(this.getEl(i, j));
				if (j != ordine - 1) {
					sb.append(", ");
				}
			}
			if (i != ordine - 1) {
				sb.append("],\n");
			} else {
				sb.append("]\n");
			}
		}
		sb.append("]\n");
		
		return sb.toString();
	}

}
