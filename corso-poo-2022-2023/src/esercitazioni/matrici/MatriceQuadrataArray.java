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
		// TODO: catch exception to test correct behavior

		return array[row * this.ordine + col];
	}

	@Override
	public void setEl(int newEl, int row, int col) {
		if (!validBounds(row, col)) throw new IndexOutOfBoundsException();
		array[row * this.ordine + col] = newEl;
	}
	
	/* below methods are made with the assumption of a null-matrix constructor
	@Override
	public MatriceQuadrata add(MatriceQuadrata m) {
		MatriceQuadrata result = new MatriceQuadrataArray(ordine);
		result.addWithThis(this);
		
		result.addWithThis(m);
		return result;
	}

	@Override
	public MatriceQuadrata mul(int num) {
		MatriceQuadrata result = new MatriceQuadrataArray(ordine);
		result.addWithThis(this);
		
		result.mulNumWithThis(num);
		return result;
	}

	@Override
	public MatriceQuadrata mul(MatriceQuadrata m) {
		MatriceQuadrata result = new MatriceQuadrataArray(ordine);
		result.addWithThis(this);
		
		result.mulWithThis(m);;
		return result;
	}
	*/
	
	@Override
	public MatriceQuadrata add(MatriceQuadrata m) {
		MatriceQuadrata result = new MatriceQuadrataArray(this);
		
		result.addWithThis(m);
		return result;
	}

	@Override
	public MatriceQuadrata mul(int num) {
		MatriceQuadrata result = new MatriceQuadrataArray(this);
		
		result.mulNumWithThis(num);
		return result;
	}

	@Override
	public MatriceQuadrata mul(MatriceQuadrata m) {
		MatriceQuadrata result = new MatriceQuadrataArray(this);
		
		result.mulWithThis(m);;
		return result;
	}

}
