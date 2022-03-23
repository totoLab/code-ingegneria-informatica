public class Esercitazione {
	
	public static void main(String[] args) {
		int[][] M = {{3,1,3},{3,3,3},{2,3,2}};
		int[] filtrati = filtraMatrice(M);
		for (int i = 0; i < M.length; i++) {
			System.out.println(filtrati[i]+" ");
		}
		System.out.println();
		int[][] M2 = {{3,1,3},{0,0,0},{2,3,2}};
		int[][] M3 = {{0,0,0},{5,5,5},{0,0,0}};
		if (aStrisce(M2)) {
			System.out.println("Matrice a strisce");
		} else {
			System.out.println("Matrice non a strisce");
		}
		
		if (aStrisce(M3)) {
			System.out.println("Matrice a strisce");
		} else {
			System.out.println("Matrice non a strisce");
		}
	}
	
	public static int[] filtraMatrice(int [][] M) {
		int dim = 0;
		for (int i = 0; i < M.length; i+=2 ) {
			if (tuttiDispari(M[i])) {
				dim++;
			}
		}
		dim *= M[0].length;
		int[] res = new int[dim];
		int k = 0;
		for (int i = 0; i <M.length; i += 2) {
			if (tuttiDispari(M[i])) {
				for (int j = 0; j < M[0].length; j++) {
					res[k] = M[i][j];
					k++;
				}
			}
		}
		return res;
	}
	
	private static boolean tuttiDispari(int[] riga) {
		for (int j = 0; j < riga.length; j++) {
			if (riga[j]%2 == 0) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Si scriva un metodo a strisce  che riceve in input 
	 * una matrice di interi e restituisce true se
	 * la matrice è a strisce orizzontali, cioè
	 * se le righe di indice dispari contengono solo zeri,
	 * mentre le righe pari non contengono zeri
	*/
	
	private static boolean aStrisce(int[][] M) {
		boolean verificato = true;
		for (int i = 0; i < M.length && verificato; i+= 2) {
			for (int  j = 0; j< M[0].length && verificato; j++) {
				if (M[i][j] == 0) {
					verificato = false;
				}
			}
		}
		for (int i = 0; i < M.length && verificato; i+= 2) {
			for (int  j = 0; j< M[0].length && verificato; j++) {
				if (M[i][j] != 0) {
					verificato = false;
				}
			}
		}
		return verificato;
	}
	// prima alternativa
	private static boolean aStrisce1(int[][] M) {
		boolean verificato = true;
		for (int i = 0; i < M.length && verificato; i+= 2) {
			for (int  j = 0; j< M[0].length && verificato; j++) {
				if (M[i][j] == 0) {
					return false;
				}
			}
		}
		for (int i = 0; i < M.length && verificato; i+= 2) {
			for (int  j = 0; j< M[0].length && verificato; j++) {
				if (M[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	// seconda alternativa
	public static boolean aStrisce2 (int[][] M) {
		for(int i= 0; i<M.length; i++) {
			for(int j= 0; j<M.length; j++) {
				if (i%2==0 && M[i][j] == 0 || i%2!=0 && M[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

}