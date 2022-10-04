package poo.giochi;
import poo.app.Monetina;

public class GiocoDelleDueMonetine {
	
	public static void main( String[] args ) {
		final int N=100, GOAL=3;
		Monetina m1=new Monetina(), m2=new Monetina();
		int c1=0, c2=0, i=0;
		while( i<N ) {
			i++;
			m1.lancia(); m2.lancia();
			System.out.println("m1="+m1+" m2="+m2);
			c1=m1.faccia()==Monetina.Esito.TESTA? c1+1 : 0;
			c2=m2.faccia()==Monetina.Esito.TESTA? c2+1 : 0;
			if( c1==GOAL || c2==GOAL ) break; //fa uscire dal ciclo
		}
		if( c1==GOAL && c2==GOAL ) System.out.println("Parita.");
		else if( c1==GOAL ) System.out.println("m1 vince.");
		else if( c2==GOAL ) System.out.println("m2 vince.");
		else System.out.println("Nessun vincitore.");
		System.out.println("Fatte "+i+" iterazioni.");
	}//main

}//GiocoDelleDueMonetine
