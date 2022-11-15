package esercitazioni.matrici;

public class Cella implements Comparable{
	
	private int row, col, val;
	
	public Cella(int i, int j, int v) {
		this.row=i;
		this.col=j;
		this.val=v;
	}
	
	public int getRow() {return row;}
	public void setRow(int index) { this.row=index;}
	public int getColumn() {return col;}
	public void setColumn(int index) { this.col=index;}
	public int getValue() {return val;}
	public void setValue(int index) { this.val=index;}
	
	@Override
	public boolean equals(Object o) {
		if ( o == null || !(o instanceof Cella) ) return false;
		if ( o == this ) return true;
		Cella c = (Cella) o;
		return this.row == c.getRow() &&
			this.col == c.getColumn() &&
			this.val == c.getValue();
	}
	
	//mantengo l'ordine per riga e per colonna
	@Override
	public int compareTo(Object o) {
		Cella c = (Cella) o;
		if ( this.getRow() < c.getRow() ) return -1;
		if ( this.getRow() > c.getRow() ) return 1;
		if ( this.getRow() == c.getRow() ) {
			if ( this.getColumn() < c.getColumn() ) return -1;
			if ( this.getColumn() > c.getColumn() ) return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "("+row+","+col+","+val+")";
	}
	
	

}
