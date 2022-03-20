
public class Esercitazione {

	public static void main(String[] args) {
		int[] l = {1,2,3,2,1,3};
  
    int[] res1 = rollup(l);
    System.out.println("Rollup:");
    for (int i=0; i<res1.length; i++) {
        System.out.println(res1[i]);
    }
    
    boolean res2 = alternati(l);
    System.out.println("Alternati: "+res2);
    
    int[] res3 = costruisciArray(l);
    System.out.println("Costruisci array:");
    for (int i=0; i<res3.length; i++)
    System.out.println(res3[i]);  
	}

    /* scrivere metodo rollup che prende in input un array l di lunghezza n (con n pari) 
    restituisce array di lunghezza n/2 il cui i-esimo elemento è pari a l[2*i]+ l[2*i+1] */
    public static int[] rollup(int[] l) {
        int[] risultato = new int[l.length/2];

        for (int i = 0; i < risultato.length; i++) {
            risultato[i] = l[2*i] + l[2*i+1];
        }

        return risultato;
    }

    /*alternatvi che riceve in input iun array l
    e restituisce true se l'array contiene valori alternatti pari e dispari,
    cioè non ci sono due elementi consecutivi entrambi pari o entrambi dispari
    e false altrimenti */
    public static boolean alternati(int[] l){
        boolean precPari = l[0] % 2 == 0;
        for (int i = 1; i < l.length; i++) {
            if ((l[i] % 2 == 0 && precPari) || (l[i] % 2 != 0 && !precPari)) { // && ha precedenza sull'||, qui le parentesi sono ridondanti
                return false;
            }
            precPari = !precPari;
            // alternativa: precPari = l[i] % 2 == 0;
        }
        return true;
    }

    /* si scriva un metodo costrusciArray che riceve in unpout un array di interi v1
    e restituisce un array di interi v2 di pari lunghezza il cui generico elemento i è così ottenuto:
    - se la media degli elementi di v1 con indice maggiore o uguale a i è maggiore o uguale a v1[i], allora v2[i] è uguale a tale media;
    - altrimenti v2[i] è uguale alla differenza tra la somma degli elementi a sinistra di v1[i] e la somma degli degli elementi alla destra di v1[i].
    Ovviamente se non ci sono elementi a sx o a dx la somma vale 0. */
    public static int[] costruisciArray(int[] v1) {
        int[] v2 = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            int sommaDx = 0;
            for (int j = 0; j < i; j++){
                sommaDx += v1[j];
            }
            int media = sommaDx/(v1.length - i);
            if (media >= v1[i]) {
                v2[i] = media;
            } else {
                int sommaSx = 0;
                for (int k = 0; k < i; i++) {
                    sommaSx += v2[k];
                }
                v2[i] = sommaDx - sommaSx + v1[i]; // non lo contiamo in nessuna somma perciò lo riaggiungiamo
            }
        }
        return v2;
    }

    /* Si scrivano:
    1) un metodo costruisciMatrice che riceve una matrice di interi M
    e restituisce una matrice di booleani M1 della stessa dimensione t.c:
        a) le celle di M1 sulla cornice esterna contengano tute false;
        b) ogni altra cella M[i][j] contiene true sse:
            la somma delle celle adiacenti a M[i][j] è uguale al valore M[i][j]
    2) un metodo verificaMatrice che riceve in ingresso una matrice di interi M
    e restituisce true sse le colonne pari sono ordinate in modo non crescente
    e quelle dispari in modo non decrescente. */
    public static boolean[][] costruisciMatrice(int[][] M) {
    	boolean[][] M1 = new boolean[M.length][M[0].length];
    	// soluzione prof
    	// ...
    	
    	// soluzione mia  	
        for (int i = 0; i < M.length; i++) {
        	for (int j = 0; j < M[0].length; j++) {
        		if (i == 0 || i == (M.length - 1) || j == 0 || j == (M[0].length - 1)) {
        			M1[i][j] = false; 
        		} else if ((M[i + 1][j] + M[i - 1][j] + M[i][j + 1] + M[i][j - 1]) == M[i][j]) {
        			M1[i][j] = true;
        		} else {
        			M1[i][j] = false;
        		}
        	}
        }
        return M1;
    }
}
