package esercitazioni.matrici;

public class MatriceQuadrataArray extends MatriceQuadrataAbstract {

	int[] array;
	
	public MatriceQuadrataArray(int ordine) {
		super(ordine);
		this.array = new int[ordine*ordine];
	}
	
	public MatriceQuadrataArray(MatriceQuadrata m) {
		this(m.getOrdine());
		int ordine = m.getOrdine();
		for (int i = 0; i < ordine; i++) {
			for (int j = 0; j < ordine; j++) {
				this.setEl(
						m.getEl(i, j), i, j);
			}
		}
	}
	
	/* getEl logic association
	 * 
	 * ordine = 3
	 * [00, 01, 02],
	 * [10, 11, 12],
	 * [20, 21, 22]
	 *
	 * [00, 01, 02, 10, 11, 12, 20, 21, 22]
	 */
	
	@Override
	public int getEl(int row, int col) {
		if (!validBounds(row, col)) throw new IndexOutOfBoundsException();
		return array[row * this.ordine + col];
	}

	@Override
	public void setEl(int row, int col, int newEl) {
		if (!validBounds(row, col)) throw new IndexOutOfBoundsException();
		array[row * this.ordine + col] = newEl;
	}
	
	@Override
	public MatriceQuadrata newInstanceMatriceQuadrata(int ordine) {
		return new MatriceQuadrataArray(ordine);
	}
	
}
