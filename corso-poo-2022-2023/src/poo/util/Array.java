package poo.util;

import java.util.Scanner;

public final class Array{
	//classe di utilit� sugli array numerici monodimensionali
	private Array(){}

	public static int ricercaLineare( int[] a, int x ){
		//ritorna l'indice di x in a, o -1 se la ricerca fallisce
		for( int i=0; i<a.length; ++i )
			if( a[i]==x ) return i;
		return -1;
	}//ricercaLineare

	public static boolean tuttiPari( int[] a){
			for( int i=0; i<a.length; ++i )
				if( a[i]%2!=0 ) return false;
			return true;
	}

	public static int posizioneMassimo(int[] v){
		if (v.length==0) return -1;
		int max=0;
		for (int i = 1; i < v.length; i++) {
			if(v[i]>v[max])
				max=i;
		}
		return max;
	}

	public static int somma(int[] v){
		int somma=0;
		for (int i = 0; i < v.length; i++) {
			somma+=v[i];
		}
		return somma;
	}

	public static int posizioneMinimo(int[] v){
		if (v.length==0) return -1;
		int min=0;
		for (int i = 1; i < v.length; i++) {
			if(v[i]<v[min])
				min=i;
		}
		return min;
	}

		public static void selectionSort( int[] a ){
			for( int j=a.length-1; j>1; --j ){
				int iMax=0;
				for( int i=1; i<=j; ++i )
					if( a[i]>a[iMax] )
						iMax=i;
				int park=a[iMax]; a[iMax]=a[j]; a[j]=park;
			}
		}//selectionSort

		public static void bubbleSort( int[] v){
			int ius=0;//inizializzazione fittizia
			for( int j=v.length-1; j>0; j=ius ){
				int scambi=0;
				for( int i=0; i<j; i++ )
				     if( v[i]>v[i+1] ){
						//scambia v[i] con v[i+1]
						int park=v[i];
						v[i]=v[i+1]; v[i+1]=park;
						scambi++; ius=i;//indice ultimo scambio
				     }
	            if( scambi==0 ) break;
			}//for esterno
		}//bubbleSort

		public static int ricercaBinaria( int[] v, int x ) {
			//PRE: v � ordinato per valori crescenti
			int inf=0, sup=v.length-1;
			while( inf<=sup ) {
				int med=(inf+sup)/2;
				if( x==v[med] ) return med;
				if( x<v[med] ) sup=med-1;
				else inf=med+1;
			}
			return -1;
		}//ricercaBinaria


	public static int[] estraiRiga(int[][] mat,int riga){
		if (riga>=mat.length || riga<0)
			return null;
		return mat[riga];
	}

	public static int[] estraiColonna(int[][] mat,int c){
		if (c>=mat[0].length || c<0)
			return null;
		int[] colonna = new int[mat[0].length];
		for(int i=0; i<mat[0].length; i++)
			colonna[i]=mat[i][c];
		return colonna;
	}

	public static int[] estraiDiagonalePrincipale(int[][] mat){
		if (mat.length!=mat[0].length)
			return null;
		int[] diagonale = new int[mat.length];
		for(int i=0; i<mat[0].length; i++)
			diagonale[i]=mat[i][i];
		return diagonale;
	}

	public static int[] estraiDiagonaleSecondaria(int[][] mat){
		if (mat.length!=mat[0].length)
			return null;
		int[] diagonale = new int[mat.length];
		for(int i=0; i<mat[0].length; i++)
			diagonale[i]=mat[i][mat.length-i-1];
		return diagonale;
	}

	public static boolean esisteSella(int[][] m){
		for(int i=0; i<m.length; i++){
			int posMassimoRiga=posizioneMassimo(estraiRiga(m,i));
			if (i==posizioneMinimo(estraiColonna(m,posMassimoRiga)))
				return true;
			int posMinimoRiga=posizioneMinimo( estraiRiga(m,i));
			if (i==posizioneMassimo(estraiColonna(m,posMinimoRiga)))
				return true;
		}
		return false;
	}

	 /*public static int[] sella(int[][] m){
		for(int i=0; i<m.length; i++){
			int posMassimoRiga=posizioneMassimo(estraiRiga(m,i));
			if (i==posizioneMinimo(estraiColonna(m,posMassimoRiga)))
				return new int[]{i,posMassimoRiga};
			int posMinimoRiga=posizioneMinimo( estraiRiga(m,i));
			if (i==posizioneMassimo(estraiColonna(m,posMinimoRiga)))
				return new int[]{i,posMinimoRiga};
		}
		return null;
	}*/

	public static void leggi(int[][] m, Scanner sc){
		System.out.println("Inserisci la matrice:");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print("M["+i+","+j+"]=");
				m[i][j]=sc.nextInt();
			}
		}
	}

	public static void stampa(int[][] m){
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.printf("%6d",m[i][j]);
			System.out.println();
		}
	}

	public static void stampa(int[] v){
		for (int i = 0; i < v.length; i++)
				System.out.printf("%6d",v[i]);
		System.out.println();
	}

	public static void stampa(String[] v){
		for (int i = 0; i < v.length; i++)
				System.out.println(v[i]);
		System.out.println();
	}


	public static void main( String[] args ){
		Integer[] v={8,2,7,-1,5,12,10};
/*
		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		int i5=ricercaLineare(v,5);
		System.out.println("5 si trova in posizione "+i5);
		selectionSort(v);
		System.out.println("Array ordinato: "+java.util.Arrays.toString(v));
		String[] a={"zorro","caso","ebano","birillo","tana","lupo","fuoco"};
		System.out.println(java.util.Arrays.toString(a));
		Array.bubbleSort(a);
		System.out.println(java.util.Arrays.toString(a));

		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		insertionSort(v, (i1,i2)->{ //il compilatore inferisce che il tipo � Integer
			if( i1>i2 ) return -1;
			if( i1.equals(i2) ) return 0;
			return 1;
		});
		System.out.println("Array finale: "+java.util.Arrays.toString(v));
*/
		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		System.out.println("Array finale: "+java.util.Arrays.toString(v));

	}//main
}//Array