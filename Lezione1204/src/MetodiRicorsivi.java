
public class MetodiRicorsivi {
	public static int fattoriale(int n) {
		if(n == 0) {
			return 1;
		}
		return n * fattoriale(n - 1);
	}

	private boolean ricercaBinariaRicorsiva(int[] a, int v, int inizio, int fine) {
		if(inizio > fine) {
			return false;
		}
		int centro = (inizio + fine) / 2;
		if(a[centro] == v) {
			return true;
		}
		if(a[centro] > v) {
			return ricercaBinariaRicorsiva(a,v,inizio,centro - 1);
		}
		return ricercaBinariaRicorsiva(a,v,centro + 1,fine);
	}
	
	public boolean ricercaBinaria(int[] a, int v) {
		return ricercaBinariaRicorsiva(a,v,0,a.length - 1);
	}
	
	// MERGE SORT
	private static int[] merge(int[] v1, int[] v2) {
		int[] ret = new int[v1.length + v2.length];
		
		int i = 0;
		int j = 0;
		int posLibera = 0;
		
		while (i < v1.length && j < v2.length) {
			if (v1[i] < v2[j]) {
				ret[posLibera] = v1[i];
				i++;
			} else {
				ret[posLibera] = v2[j];
				j++;
			}
			posLibera++;
		}
		
		while (i < v1.length) {
			ret[posLibera] = v1[i];
			i++;
			posLibera++;
		}
		
		while (j < v2.length) {
			ret[posLibera] = v2[j];
			j++;
			posLibera++;
		}
		
		return ret;
	}
	
	public static int[] mergeSort(int[] v) {
		int n = v.length;
		if (n == 1) {
			return v;
		}
		
		int[] primaMeta = new int[n / 2];
		int[] secondaMeta = new int[n - (n / 2)];
		
		for (int i = 0; i < n / 2; i++) {
			primaMeta[i] = v[i];
		}
		
		for (int i = n / 2; i < n; i++) {
			primaMeta[i - (n / 2)] = v[i];
		}
		
		primaMeta = mergeSort(primaMeta);
		secondaMeta = mergeSort(secondaMeta);
		
		return merge(primaMeta, secondaMeta);
	}
	
	// QUICK SORT
	private static void scambia(int[] v, int i, int j) {
		int t = v[i];
		v[i] = v[j];
		v[j] = t;
	}
	
	private static int partiziona(int[] v, int inizio, int fine) {
		int pivot = v[fine];
		int posLibera = inizio;
		for (int j = inizio; j < fine; j++) {
			if (v[j] <= pivot) {
				scambia(v, posLibera, j);
				posLibera++;
			}
		}
		scambia(v, posLibera, fine);
		return posLibera;
	}
	
	private static void quickSort(int[] v, int inizio, int fine) {
		if (inizio < fine) {
			int p = partiziona(v, inizio, fine);
			quickSort(v, inizio, p - 1);
			quickSort(v, p + 1, fine);
		}
	}
	
	public void quickSort(int[] v) {
		quickSort(v, 0, v.length - 1);
	}
	
}


