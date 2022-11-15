package esercitazioni.matrici;

import java.util.Iterator;

public class MatriceQuadrataArrayDiArray extends MatriceQuadrataAbstract {

	private final int[][] mat;
	 
	public MatriceQuadrataArrayDiArray(int ordine){
		super(ordine);
		mat = new int[ordine][ordine];
	}

	@Override
	public int getEl(int row, int col) throws IndexOutOfBoundsException {
		return mat[row][col];
	}

	@Override
	public void setEl(int row, int col, int newEl) throws IndexOutOfBoundsException {
		mat[row][col] = newEl;
	}

	@Override
	public MatriceQuadrata newInstanceMatriceQuadrata(int ordine) {
		return new MatriceQuadrataArrayDiArray(ordine);
	}
	
	

}
