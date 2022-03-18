
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
}
