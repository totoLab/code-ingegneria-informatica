package tracceesame.progetti.sudoku;

public class CellaSudoku {

	private int i, j, valore;
	
	public CellaSudoku(int i, int j, int valore) {
		if (i < 0 || j < 0 || valore < 0 || i > 9  || j > 9 || valore > 9 ) throw new IllegalArgumentException();
		this.i = i;
		this.j = j;
		this.valore = valore;
	}
	
	public CellaSudoku(CellaSudoku c, int valore) {
		this(c.i, c.j, valore);
	}
	
	@Override
	public String toString() {
		return "{" + i + ", " + j + "} = " + valore;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getValore() {
		return valore;
	}

	public void setValore(int v) {
		this.valore = v;
	}
}
