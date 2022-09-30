package poo.date;

import java.util.GregorianCalendar;
public class Data{
	private final int G, M, A;
	private enum Cosa{ GIORNO, MESE, ANNO };
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

	public int get( Cosa cosa ){
		switch( cosa ){
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
		//TODO
		return null;
	}//giornoPrima

	public int distanza( Data d ){
		//PRE: this è precedente a d
		return 0;
	}//distanza

	public String toString(){
		return ""+G+"/"+M+"/"+A;
	}//toString

	public static void main( String[] args ){
		Data oggi=new Data();
		System.out.println("Giorno odierno="+oggi.get(Data.Cosa.GIORNO));
		Data domani= oggi.giornoDopo();
		System.out.println("Oggi e' il "+oggi);
		System.out.println("Domani e' il "+domani);
		Data d=new Data( 28, 2, 2022 );
		Data ds=d.giornoDopo();
		System.out.println("Data successiva a "+d+" e' "+ds);
	}//main

}//Data