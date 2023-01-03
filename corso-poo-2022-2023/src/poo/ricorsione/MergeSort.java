package poo.ricorsione;

public class MergeSort {
	
	public static<T extends Comparable<? super T>> void mergeSort(T[] l, int inf, int sup) {
		if (inf < sup) {
			int med = (inf + sup) / 2;
			mergeSort(l, inf, med);
			mergeSort(l, med + 1, sup);
			merge(l, inf, med, sup);
		}
	}

	@SuppressWarnings("unchecked")
	private static<T extends Comparable<? super T>> void merge(T[] l, int inf, int med, int sup) {
		T[] aux = (T[]) new Comparable[sup - inf + 1];
		int i = inf, j = med + 1, k= 0;
		
		// merging
		while (i <= med && j <= sup) {
			if (l[i].compareTo(l[j]) <= 0) {
				aux[k] = l[i];
				i++; k++;
			} else {
				aux[k] = l[j];
				j++; k++;
			}
		}
		
		// copia rimanenti
		while (i <= med) {
			aux[k] = l[i]; i++;
		}
		while (j <= sup) {
			aux[k] = l[j]; j++;
		}
		
		// trasferimento nel vettore originale
		for (k = 0; k < aux.length; k++) {
			l[inf + k] = aux[k];
		}
	}
	
	public static<T extends Comparable<? super T>> void mergeSortIterativo(T[] l, int inf, int sup) {
		// TODO
	}
	
}
