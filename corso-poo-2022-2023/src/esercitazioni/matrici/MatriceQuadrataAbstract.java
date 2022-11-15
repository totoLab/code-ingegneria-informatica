package esercitazioni.matrici;

import java.util.Iterator;

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
    
	public MatriceQuadrata add(MatriceQuadrata m){
		if( ordine!=m.getOrdine() )
			throw new IllegalArgumentException("Matrici incompatibili in add");
		MatriceQuadrata somma=this.newInstanceMatriceQuadrata(ordine);
		for( int i=0; i<ordine; i++ )
			for( int j=0; j<ordine; j++ )
			{
				somma.setEl( i,j, this.getEl(i,j)+m.getEl(i,j) );
			}
		return somma;
	}

	public MatriceQuadrata mul(MatriceQuadrata m){
		if( ordine != m.getOrdine() )
			throw new IllegalArgumentException("Matrici incompatibili in mul(MatriceQuadrata)");
		MatriceQuadrata prodotto=this.newInstanceMatriceQuadrata(ordine);
		for( int i=0; i<ordine; i++ )
			for( int j=0; j<ordine; j++ ){
				int ps=0;
				for( int k=0; k<ordine; k++)
					ps+=this.getEl(i,k)*m.getEl(k,j);
				prodotto.setEl( i, j, ps );
			}
		return prodotto;
	}

	public MatriceQuadrata mul(int scalare) {
		MatriceQuadrata prodotto=this.newInstanceMatriceQuadrata(ordine);
		for( int i=0; i<ordine; i++ )
			for( int j=0; j<ordine; j++ )
				prodotto.setEl( i,j, this.getEl(i,j)*scalare );
		return prodotto;
	}
	
	public void resetAllElements() {
		for( int i=0; i<ordine; i++ )
			for( int j=0; j<ordine; j++ )
				this.setEl(i,j,0);
	}

	public boolean puntoDiSella() {
		for(int i=0; i<this.ordine; i++) {
			int minRow = indexMinOnRow(i); //INDEX COLONNA
			int maxCol = indexMaxOnCol(minRow); //INDEX ROW
			if(maxCol==i) {
				System.out.println("IL PUNTO DI SELLA E' IN POSIZIONE: "+i+" "+minRow+" E VALE: "+this.getEl(i, minRow));
				return true;
			}
		}
		return false;
	}

	private int indexMinOnRow(int r) {
		int min = this.getEl(r, 0);
		int index = 0;
		for(int i=1; i<this.ordine;i++) {
			if(this.getEl(r, i)<min) {
				min=this.getEl(r, i);
				index=i;
			}
		}
		return index;
	}
	
	//RESTITUISCE LA RIGA DELL'ELEMENTO DELLA COLONNA MASSIMO
	private int indexMaxOnCol(int c) {
		int max = this.getEl(0,c);
		int index = 0;
		for(int i=1; i<this.ordine;i++) {
			if(this.getEl(i, c)>max) {
				max=this.getEl(i,c);
				index=i;
			}
		}
		return index;
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

	public boolean equals( Object x ){
		if( !(x instanceof MatriceQuadrata) ) return false;
		MatriceQuadrata m=(MatriceQuadrata)x;
		//devo usare getOrdine perchè 'm' e' una MatriceQuadrata e non una MatriceQuadrataAbstract
		if( ordine!=m.getOrdine() ) return false;
		for( int i=0; i<ordine; i++ )
			for( int j=0; j<ordine; j++ )
				if( this.getEl(i,j)!=m.getEl(i,j) ) return false;
		return true;
	}

	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
