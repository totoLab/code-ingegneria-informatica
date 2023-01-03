package poo.ricorsione;
import java.util.*;
public class NRegine extends Backtracking<Integer,Integer>{
	private boolean [][]board;
	private int n, nrSol=0;

	public NRegine(int n) {
		if( n<=3 ) throw new IllegalArgumentException();
		board=new boolean[n][n];
		this.n=n;
	}

	protected List<Integer> puntiDiScelta(){
		ArrayList<Integer> ps=new ArrayList<>();
		for( int i=0; i<n; ++i ) ps.add(i);
		return ps;
	}
	protected Collection<Integer> scelte( Integer p ){
		List<Integer> s=new ArrayList<>();
		for( int i=0; i<n; ++i ) s.add(i);
		return s;
	}

	public void risolvi() {
		tentativo( puntiDiScelta(), 0 );
	}//risolvi

	protected boolean esisteSoluzione( Integer p ) {
		return p==board.length-1;
	}//esisteSoluzione
	
	protected boolean assegnabile( Integer r, Integer c ) {
		//� assegnabile una regina sulla colonna c della riga r?
		//verifica i vincoli di essere sotto attacco
		//verifica attacco a NORD
		for( int i=r-1; i>=0; --i )
			if( board[i][c] ) return false;
		//verifica attacco a NORD-OVEST
		for( int i=r-1,j=c-1; i>=0 && j>=0; --i,--j )
			if( board[i][j] ) return false;
		//verifica attacco a NORD-EST
		for( int i=r-1,j=c+1; i>=0 && j<=n-1; --i,++j )
			if( board[i][j] ) return false;
		return true;
	}//assegnabile

	protected void assegna( Integer r, Integer c ) {
		board[r][c]=true;
	}//assegna

	protected void deassegna( Integer r, Integer c ) {
		board[r][c]=false;
	}//deassegna

	protected void scriviSoluzione( Integer p ) {
		nrSol++;
		System.out.print(nrSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j )
				if( board[i][j] ) {
					System.out.print("<"+i+","+j+">");
					break; //interrompe il ciclo di for interno
				}
		}
		System.out.println();
	}//scriviSoluzione

	public static void main( String[] args ) {
		new NRegine(4).risolvi();
		System.out.println("done");
	}//main

}//NRegine
