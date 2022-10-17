package poo.util;

import java.util.GregorianCalendar;
public class Data{
	private final int G, M, A;
	public enum Tipologia{ GIORNO, MESE, ANNO };
	public Data(){
		GregorianCalendar gc=new GregorianCalendar();
		G=gc.get( GregorianCalendar.DAY_OF_MONTH );
		M=gc.get( GregorianCalendar.MONTH )+1;
		A=gc.get( GregorianCalendar.YEAR );
	}
	public Data( int g, int m, int a ){
		if( g<1 || g>durata(m,a) || m<1 || m>12 || a<0 )
			throw new IllegalArgumentException();
		G=g; M=m; A=a;
	}
	public Data( Data d ){
		G=d.G; M=d.M; A=d.A;
	}

	public int get( Tipologia t ){
		switch( t ){
			case GIORNO: return G;
			case MESE: return M;
			default : return A;
		}
	}//get

	public static boolean bisestile( int a ){
		if( a<0 ) throw new IllegalArgumentException();
		if( a%4!=0 ) return false;
		if( a%100==0 && a%400!=0 ) return false;
		return true;
	}//bisestile

	public static int durata( int m, int a ){
		if( m<1 || m>12 || a<0 ) throw new IllegalArgumentException();
		int d=0;
		switch( m ){
			case 1: case 3: case 5: case 7: case 8: case 10: case 12: d=31; break;
			case 2: d=bisestile(a)? 29 : 28; break;
			default : d=30;
		}
		return d;
	}//durata

	public Data giornoDopo(){
		int g=0, m=0, a=0; //inizializzazioni fittizie
		if( G==durata(M,A) ){
			if( M==12 ){
				g=1; m=1; a=A+1;
			}
			else{
				g=1; m=M+1; a=A;
			}
		}
		else{
			g=G+1; m=M; a=A;
		}
		return new Data(g,m,a);
	}//Data

	public Data giornoPrima(){
		int g=0, m=0, a=0; //inizializzazioni fittizie
		if (G == 1) {
			if (M == 1) {
				if ( A == 0 ) {
					throw new Error("Non esiste il giorno prima del primo giorno disponibile");
				} else {
					g = 31; m = 12; a = A - 1;
				}
			} else {
				g = durata(M - 1, A); m = M - 1; a = A;
			}
		} else {
			g = G - 1; m = M; a = A;
		}
		
		return new Data(g, m, a);
	}//giornoPrima

	public int distanzaInefficiente( Data d ){
		//PRE: this è precedente a d
		Data n = new Data(this);
		int giorni = 0;
		while (!n.equals(d)) {
			n = n.giornoDopo();
			giorni++;
			//System.out.println(giorni);
		}
		return giorni;
	}//distanzaDumb
	
	private static int numeroBisestili(int a1, int a2) {
		int min = Math.min(a1, a2);
		int cont = 0;
		for (int i = min; i < min + Math.abs(a1 - a2); i++) {
			if (bisestile(min)) {
				cont++;
			}
		}
		return cont;
	}
	
	private int giorniAFineAnno(int m, int a) {
		int somma = 0;
		for (int i = m; i < 12; i++) {
			somma += durata(m, a);
		}
		return somma;
	}
	
	private int giorniDaInizioAnno(int m, int a) {
		int somma = 0;
		for (int i = 0; i <= m; i++) {
			somma += durata(m, a);
		}
		return somma;
	}
	
	private int giorniAFineMese(int g, int m, int a) {
		return durata(m, a) - g;
	}
	
	private int giorniDaInizioMese(int g, int m, int a) {
		return g;
	}
	
	public int distanza (Data d) {
		if (this.equals(d)) return 0;
		// presupponendo this precedente al parametro d
		
		// handling anni
		int a = d.A - this.A;
		int gAnniMezzo = 0;
		int bisestiliInMezzo = numeroBisestili(this.A + 1, d.A - 1);
		gAnniMezzo = (a - bisestiliInMezzo) * 365 + bisestiliInMezzo * 366;
		
		//handling mesi
		int m = d.M - this.M;
		int gMesiInMezzo = 0;
		gMesiInMezzo = giorniAFineAnno(this.M + 1, this.A) + giorniDaInizioAnno(d.M - 1, d.A);
		
		//handling giorni -> somma
		int g = d.G - this.G;
		int gGiorni = 0;
		if (g > 0) {
			gGiorni = giorniAFineMese(this.G, this.M, this.A) + giorniDaInizioMese(d.G, d.M, d.A);
		}
		return gAnniMezzo + gMesiInMezzo + gGiorni;
	}//distanza

	public String toString(){
		return ""+G+"/"+M+"/"+A;
	}//toString

	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Data)) return false;
		
		Data d = (Data) o;
		return this.G == d.G &&
				this.M == d.M &&
				this.A == d.A;
	}
	
	public static void main( String[] args ){
		Data oggi=new Data();
		Data d=new Data( 10, 10, 2032 );
		int distanzaTesting = oggi.distanza(d);
		int distanzaAffidabile = oggi.distanzaInefficiente(d);
		if (distanzaTesting == distanzaAffidabile) {
			System.out.println("Test passed.");
			System.out.println("Al " + d + " da " + oggi + " mancano "+ distanzaTesting + " giorni.");
		} else {
			System.out.println("Test failed -> expected: " + distanzaAffidabile + ", got: " + distanzaTesting );
		}
	}//main

}//Data