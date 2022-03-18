
public class Esercitazione {

	public static void main(String[] args) {
        
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
    public static int[] costrusciArray(int[] v1) {
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
                v2[i] = sommaDx - sommaSx;
            }
        }
        return v2;
    }
}
