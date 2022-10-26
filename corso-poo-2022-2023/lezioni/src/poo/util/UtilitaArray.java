package poo.util;

public class UtilitaArray {

	private UtilitaArray() {}
	
	public static boolean tuttiPari(int[] v) {
		for(int i=0; i<v.length; i++)
			if (v[i]%2!=0)
				return false;
		return true;
	}
	
	public static int ricercaLineare(int[] v, int x) {
		for(int i=0; i<v.length; i++)
			if (v[i]==x)
				return i;
		return -1;
	}
	
	public static int ricercaBinaria(int[] v, int x) {
		int inf=0; int sup=v.length-1;
		while(inf<=sup) {
			int med=(inf+sup)/2;
			if (v[med]==x)
				return med;
			if (x<v[med])
				sup=med-1;
			else
				inf=med+1;
		}
		return -1;
	}
	
	public static int[] estraiRiga(final int[][] mat, final int r) {
		return mat[r];
	}
	 
}
