
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
	
	
}


