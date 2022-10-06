package poo.util;

public final class Matrix {
	private Matrix() {}
	
	public static int[][] add( int[][]a, int[][] b ){
		//verifica che tutte le righe di a abbiano la stessa lunghezza
		for( int i=1; i<a.length; ++i )
			if( a[i].length != a[0].length )
				throw new IllegalArgumentException("Prima matrice irregolare.");
		//verifica che tutte le righe di b abbiano la stessa lunghezza
		for( int i=1; i<b.length; ++i )
			if( b[i].length != b[0].length )
				throw new IllegalArgumentException("Seconda matrici irregolare.");	
		//verifica che a e b siano compatibili per l'addizione
		if( a.length != b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per la addizione.");

		int[][] c=new int[a.length][a[0].length]; //matrice somma
		
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				c[i][j]=a[i][j]+b[i][j];
		return c;
	}//add
	
	public static int[][] mul( int[][]a, int[][]b ){
		//verifica che tutte le righe di a abbiano la stessa lunghezza
		for( int i=1; i<a.length; ++i )
			if( a[i].length != a[0].length )
				throw new IllegalArgumentException("Prima matrice irregolare.");
		//verifica che tutte le righe di b abbiano la stessa lunghezza
		for( int i=1; i<b.length; ++i )
			if( b[i].length != b[0].length )
				throw new IllegalArgumentException("Seconda matrici irregolare.");	
		//verifica che a e b siano compatibili per la moltiplicazione
		if( a[0].length != b.length )
			throw new IllegalArgumentException("Matrici incompatibili per la moltiplicazione.");
		
		int[][] c=new int[a.length][b[0].length]; //matrice prodotto
		
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<b[0].length; ++j ) {
				//fai prodotto scalare tra riga i di a e colonna j di b
				int ps=0;
				for( int k=0; k<a[0].length; ++k )
					ps=ps+a[i][k]*b[k][j];
				c[i][j]=ps;
			}
		return c;
	}//mul
	
	public static double[][] add( double[][] a, double[][] b ){
		//verifica che tutte le righe di a abbiano la stessa lunghezza
		for( int i=1; i<a.length; ++i )
			if( a[i].length != a[0].length )
				throw new IllegalArgumentException("Prima matrice irregolare.");
		//verifica che tutte le righe di b abbiano la stessa lunghezza
		for( int i=1; i<b.length; ++i )
			if( b[i].length != b[0].length )
				throw new IllegalArgumentException("Seconda matrici irregolare.");	
		//verifica che a e b siano compatibili per l'addizione
		if( a.length != b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per la addizione.");

		double[][] c=new double[a.length][a[0].length];
		
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				c[i][j]=a[i][j]+b[i][j];
		return c;
	}//add
	
	public static double[][] mul( double[][] a, double[][]b ){
		//verifica che tutte le righe di a abbiano la stessa lunghezza
		for( int i=1; i<a.length; ++i )
			if( a[i].length != a[0].length )
				throw new IllegalArgumentException("Prima matrice irregolare.");
		//verifica che tutte le righe di b abbiano la stessa lunghezza
		for( int i=1; i<b.length; ++i )
			if( b[i].length != b[0].length )
				throw new IllegalArgumentException("Seconda matrici irregolare.");	
		//verifica che a e b siano compatibili per la moltiplicazione
		if( a[0].length != b.length )
			throw new IllegalArgumentException("Matrici incompatibili per la moltiplicazione.");
		
		double[][] c=new double[a.length][b[0].length]; //matrice prodotto
		
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<b[0].length; ++j ) {
				//fai prodotto scalare tra riga i di a e colonna j di b
				double ps=0;
				for( int k=0; k<a[0].length; ++k )
					ps=ps+a[i][k]*b[k][j];
				c[i][j]=ps;
			}
		return c;
	}//mul
	
	public static double[][] minore( double[][] a, int i, int j ){
		//a matrice quadrata o rettangolare
		//i deve essere un indice di riga ammissibile di a
		//j deve essere un indice di colonna ammissibile di a
		//Obiettivo: creare e restituire la sotto matrice che
		//si ottiene da a eliminando la riga i e la colonna j
		return null; //TODO
	}//minore
	
	public static void print( int[][] m ) {
		for( int i=0; i<m.length; ++i ) {
			for(int j=0; j<m[0].length; ++j )
				System.out.printf( "%7d", m[i][j] );
			System.out.println();
		}		
	}//print
	
	public static void print( double[][] m ) {
		for( int i=0; i<m.length; ++i ) {
			for(int j=0; j<m[0].length; ++j )
				System.out.printf( "%7.2f", m[i][j] );
			System.out.println();
		}		
	}//print
	
	public static void main( String[] args ) {
		int[][] m1={ //3*2
				{1,2},
				{3,5},
				{8,9}
		};
		System.out.println("Prima matrice m1:");
		Matrix.print(m1);
		int[][] m2={ //3*2
				{10,4},
				{-3,2},
				{7,8}
		};
		System.out.println();
		System.out.println("Seconda matrice m2:");
		Matrix.print(m2);
		int[][] m3={//2*4
				{2,5,1,7},
				{3,8,4,6}
		};
		System.out.println();
		System.out.println("Terza matrice m3:");
		Matrix.print(m3);
		
		int[][] s=Matrix.add(m1,m2);
		int[][] p=Matrix.mul(m1, m3);
		
		System.out.println();
		System.out.println("Matrice somma m1+m2:");
		Matrix.print(s);
		System.out.println();
		System.out.println("Matrice prodotto m1*m3:");
		Matrix.print(p);
		System.out.println();
		
	}//main
	
}//Matrix
