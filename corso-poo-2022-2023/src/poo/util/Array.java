package poo.util;

public final class Array {
	private Array() {}
	
	public static double prodottoScalare( double[] v1, double[] v2 ) {
		//realizza il prodotto scalare tra v1 e v2 e lo ritorna
		return 0; //TODO
	}//prodottoScalare
	
	public static int ricercaLineare( int[] a, int x ) {
		for( int i=0; i<a.length; ++i ) {
			if( a[i]==x ) return i;
		}
		return -1;
	}//ricercaLineare
	
	public static int ricercaBinaria( int[] a, int x ) {
		//PRE: a è ordinato per valori crescenti
		int inf=0, sup=a.length-1;
		while( inf<=sup ) {
			int med=(inf+sup)/2;
			if( a[med]==x ) return med;
			if( a[med]>x ) sup=med-1;
			else inf=med+1;
		}
		return -1;
	}//ricercaBinaria
	
	public static boolean ordinato( int[] a ) {
		return false; //TODO
	}//ordinato
	
	public static void selectionSort( int[] v ) {
		for( int j=v.length-1; j>0; --j ) {
			int indMax=0;
			for( int i=1; i<=j; ++i )
				if( v[i]>v[indMax] ) indMax=i;
			//scambia v[j] con v[indMax]
			int park=v[j]; v[j]=v[indMax]; v[indMax]=park;
		}
	}//selectionSort
	
	public static void bubbleSort( int[] v ) {
		boolean scambi=true; //pessimismo
		int limite=v.length-1, ius=-1;
		while( scambi ) {
			scambi=false; //ottimismo Watson!
			for( int i=0; i<limite; ++i ) {
				if( v[i]>v[i+1] ) {
					int tmp=v[i]; v[i]=v[i+1]; v[i+1]=tmp;
					ius=i; scambi=true; 
				}
			}
			limite=ius;
		}
	}//bubbleSort
	
	public static void insertionSort( int[] v ) {
		for( int i=0; i<v.length; ++i ) {
			int x=v[i]; //togli v[i]
			int j=i;
			while( j>0 && v[j-1]>x ) {
				//sposta in avanti v[j-1]
				v[j]=v[j-1]; j--;
			}
			//poni x in v[j]
			v[j]=x;		}
	}//insertionSort
	
	public static void main( String[] args ) {
		int[] w= {10,9,8,7,6,5,4,3,2,1};
		System.out.println( java.util.Arrays.toString(w) );
		Array.insertionSort(w);
		System.out.println( java.util.Arrays.toString(w) );
		//TESTARE TUTTI I METODI
	}//main
	
}//Array
