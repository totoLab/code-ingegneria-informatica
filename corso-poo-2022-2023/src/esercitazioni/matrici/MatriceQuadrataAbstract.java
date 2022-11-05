package esercitazioni.matrici;

public abstract class MatriceQuadrataAbstract implements MatriceQuadrata {
    
	protected int ordine;
	
	public MatriceQuadrataAbstract(int ordine) {
		if (ordine < 0) throw new IllegalArgumentException();
		this.ordine = ordine;
	}
	
	public MatriceQuadrataAbstract(MatriceQuadrata m) {
		this(m.getOrdine());
		int ordine = m.getOrdine();
		for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						m.getEl(i, j), i, j);
			}
		}
	}
	
	public int getOrdine() {
		return this.ordine;
	}
	
	public boolean validBounds(int row, int col) {
		return row < ordine && col < ordine;
	}
	
	public abstract int getEl(int row, int col);
	
	public abstract void setEl(int newEl, int row, int col);
	
	public void addWithThis(MatriceQuadrata m) {
		int ordine = m.getOrdine();
		for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						this.getEl(i, j) + m.getEl(i, j), i, j
						);
			}
		}
	}
	
	public abstract MatriceQuadrata add(MatriceQuadrata m);

    public void mulNumWithThis(int num) {
    	int ordine = this.getOrdine();
    	for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						this.getEl(i, j) * num, i, j
						);
			}
    	}
    }
    
    public abstract MatriceQuadrata mul(int num);

    public void mulWithThis(MatriceQuadrata m) {
    	int ordine = this.getOrdine();
    	for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				int ps = 0;
				for (int k = 0; k < ordine; k++) {
					ps += this.getEl(i, k) * m.getEl(k, j);
				}
				this.setEl(ps, i, j);
			}	
		}
    }
    
    public abstract MatriceQuadrata mul(MatriceQuadrata m);
}
