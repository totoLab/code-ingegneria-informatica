package poo.util;

public final class Matrix {
	private Matrix() {}
	
	public static int[][] add( int[][]a, int[][] b ){
		if (!(eQuadrata(a) && eQuadrata(b))) throw new IllegalArgumentException("Matrici non quadrate.");
		if (a.length != b.length) throw new IllegalArgumentException("Matrici di ordine diverso");
		
		int n = a.length;
		int[][] sum = new int[n][n];
		for( int i=0; i<n; i++ )
			for( int j=0; j<n; j++)
				sum[i][j]=a[i][j]+b[i][j];
		return sum;
	}//add
	
	public static int[][] mul( int[][]a, int[][]b ){
		if (!(eQuadrata(a) && eQuadrata(b))) throw new IllegalArgumentException("Matrici non quadrate.");
		if (a.length != b.length) throw new IllegalArgumentException("Matrici di ordine diverso");
		int n = a.length;
		int[][] prod=new int[n][n];
		
		for( int i=0; i<n; ++i )
			for( int j=0; j<n; ++j ) {
				//fai prodotto scalare tra riga i di a per colonna j di b
				prod[i][j]=0;
				for( int k=0; k<n; ++k )
					prod[i][j]=prod[i][j]+a[i][k]*b[k][j];
			}
		return prod;
	}//mul
	
	private static boolean eQuadrata(int[][] m) {
		for (int i = 0; i < m.length; i++ ) {
			if( m[i].length != m.length ) {
				return false;
			}
		}
		return true;
	}

	public static double[][] add( double[][] m1, double[][] m2 ){
		//accetta due matrici quadrate o rettangolari compatibili alla somma
		//crea e restituisce la matrice somma di m1 ed m2
		return null; //TODO
	}//add
	
	public static double[][] mul( double[][] m1, double[][] m2 ){
		//riceve due matrici compatibili rispetto alla moltiplicazione
		//crea e restituisce la matrice prodotto
		return null; //TODO
	}//mul
	
	public static double[][] minore( double[][] m, int i, int j ){
		//a matrice quadrata o rettangolare
		//i deve essere un indice di riga ammissibile di a
		//j deve essere un indice di colonna ammissibile di a
		//Obiettivo: creare e restituire la sotto matrice che
		//si ottiene da a eliminando la riga i e la colonna j
		return null; //TODO
	}//minore
	
	public static void main( String[] args ) {
		//testare i metodi sviluppati
		//TODO
	}//main
	
}//Matrix
