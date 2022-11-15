package esercitazioni.matrici;

import java.util.Iterator;

public class MatriceQuadrataSparsa extends MatriceQuadrataAbstract{

	private int numeroElementiNonNulli=0;
	private Cella[] matrix;
	
	public MatriceQuadrataSparsa(int ordine) throws NegativeArraySizeException {
		super(ordine);
		matrix=new Cella[ordine]; 
	}

	@Override
	public int getEl(int i, int j) throws IndexOutOfBoundsException {
		if (i<0 || i>=getOrdine() || j<0 || j>=getOrdine())
			throw new IndexOutOfBoundsException();
		//ITERANDO FINO AL NUM DI ELEM NON NULLI, HO LA CERTEZZA DI NON INTERROGARE ELEM NULL
		int index=searchIndex(i,j);
		if(index!=-1) {
			return matrix[index].getValue();
		}else {
			return 0;
		}
	}
	
	private int searchIndex(int i, int j) {
		//ITERANDO FINO AL NUM DI ELEM HO LA CERTEZZA DI NON INTERROGARE POSIZIONI A NULL
		for(int k=0; k<numeroElementiNonNulli; k++) {
			if(matrix[k].getRow()==i && matrix[k].getColumn()==j) {
				return k;
			}
			if(matrix[k].getRow()>i || (matrix[k].getRow()==i && matrix[k].getColumn()>j))
				return -1;
		}
		return -1;
	}

	@Override
	public void setEl(int i, int j, int v) throws IndexOutOfBoundsException {
		if (i<0 || i>=getOrdine() || j<0 || j>=getOrdine())
			throw new IndexOutOfBoundsException();
		//SE GIA' ESISTE FACCIO L'UPDATE
		int index = searchIndex(i,j);
		if(index!=-1) {
			//E' GIA' PRESENTE
			setVal(index,v);
		}else{
			//NON E' PRESENTE
			if(v!=0) {
				//SE E' !=0 LO AGGIUNGO
				addVal(i,j,v);
			}
		}
	}
	
	private void setVal(int k, int v) {
		if(v!=0) {
			matrix[k].setValue(v);
		}else {
			//SE IL VALORE DA INSERIRE E' ZERO, DEVO RIMUOVERLO SE GIA' PRESENTE: compatto
			remove(k);
		}
	}
	
	private void remove(int k) {
		for(int i=k; i<numeroElementiNonNulli;i++) {
			matrix[i]=matrix[i+1];
			//in caso di sostituzione in ultima posizione, mette null
		}
		numeroElementiNonNulli--;
	}
	
	private void addVal(int i,int j, int v) {
		Cella c = new Cella(i,j,v);
		
		int indexToInsert = searchNewIndex(c);
		numeroElementiNonNulli++;
		if(indexToInsert==-1) {
			//AGGIUNGO IN CODA
			matrix[numeroElementiNonNulli-1]=c;
		}else {
			//SPOSTO IN AVANTI DI 1 DALLA POSIZIONE
			for(int k=numeroElementiNonNulli-1; k>indexToInsert;k--) {
				matrix[k]=matrix[k-1];
			}
			matrix[indexToInsert]=c;
		}
		
		
		if(numeroElementiNonNulli==matrix.length-1) {
			rialloca();
		}
	}
	
	private int searchNewIndex(Cella c) {
		for(int k=0; k<numeroElementiNonNulli; k++) {
			if(matrix[k].compareTo(c)>0) {
				return k;
			}
		}
		return -1;
	}
	
	private void rialloca(){
		Cella[] n = new Cella[matrix.length*2];
		for(int i=0; i<numeroElementiNonNulli;i++) {
			n[i]=matrix[i];
		}
		matrix=n;
	}

	@Override
	public MatriceQuadrata newInstanceMatriceQuadrata(int ordine) {
		return new MatriceQuadrataSparsa(ordine);
	}

	@Override
	public Iterator iterator() {
		return new MatrixIterator();
	}

	/*
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<numeroElementiNonNulli;i++)
			sb.append(matrix[i]+" ");
		return sb.toString();
	}
	*/
	
	class MatrixIterator implements Iterator{

		private int i=0,j=-1;
		
		@Override
		public boolean hasNext() {
			if(j+1<ordine) {
				return true;
			}else {
				if(i+1<ordine) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Object next() {
			if(j+1<ordine) {
				j++;
			}
			else {
				j=0;
				if(i+1<ordine) {
					i++;
				}else {
					throw new IllegalArgumentException();
				}
			}
			return getEl(i,j);
		}
		
		@Override
		public void remove() {
			setEl(i,j,0);
		}
		
	}
	
}
