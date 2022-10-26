package poo.date;
import java.util.*;

public class Data implements Comparable<Data>{
	private final int G, M, A;

	public enum Cosa { GIORNO, MESE, ANNO }

	public Data(){
		GregorianCalendar gc=new GregorianCalendar();
		G=gc.get( GregorianCalendar.DAY_OF_MONTH );
		M=gc.get( GregorianCalendar.MONTH )+1;
		A=gc.get( GregorianCalendar.YEAR );
	}//Data
	public Data( int g, int m, int a ){
		if( a<0 || m<1 || m>12 || g<1 || g>durataMese(m,a) )
			throw new IllegalArgumentException();
		this.G=g; this.M=m; this.A=a;
	}//Data
	public Data( Data d ){
		G=d.G; M=d.M; A=d.A;
	}//Data

	public int get( Cosa cosa ){
		switch( cosa ){
			case GIORNO: return G;
			case MESE: return M;
			default : return A;
		}
	}//get

	public static boolean bisestile( int a ){
		if( a<0 )
			throw new IllegalArgumentException();
		if( a%4!=0 ) return false;
		if( a%100==0 && a%400!=0 ) return false;
		return true;
	}//bisestile
	public static int durataMese( int m, int a ){
		if( m<1 || m>12 || a<0 )
			throw new IllegalArgumentException();
		int durata;
		switch( m ){
			case 1: case 3: case 5: case 7: case 8:
			case 10: case 12: durata=31; break;
			case 2: durata=bisestile(a) ? 29:28; break;
			default: durata=30;
		}//switch
		return durata;
	}//durataMese

	public Data giornoDopo(){
		int durata=durataMese( M, A );
		int g1, m1, a1;
		if( G==durata ){
			g1=1;
			if( M==12 ){ m1=1; a1=A+1; }
			else{ m1=M+1; a1=A; }
		}
		else{ g1=G+1; m1=M; a1=A; }
		return new Data( g1,m1,a1 );
	}//giornoDopo

	public Data giornoPrima(){
		//lasciato per esercizio
		return null; //TODO
	}//giornoPrima

	public int distanza( Data d ){
		Data d1=new Data(this), d2=new Data(d);
		if( d1.compareTo(d2)==0 ) return 0;
		if( d1.compareTo(d2)>0 ){ Data tmp=d1; d1=d2; d2=tmp; }
		int cnt=0;
		do{
			cnt++;
			d1=d1.giornoDopo();
		}while( d1.compareTo(d2)<0 );
		return cnt;
	}//distanza

	public String toString(){
		String s="";
		if(G<10) s=s+"0";
		s=s+G+"/";
		if(M<10) s=s+"0";
		s=s+M+"/"+A;
		return s;
	}//toString

	public int compareTo( Data d ){
		if( this.A<d.A || this.A==d.A && this.M<d.M || 
			this.A==d.A && this.M==d.M && this.G<d.G ) return -1;
		if( this.A==d.A && this.M==d.M && this.G==d.G ) return 0;
		return 1;
	}//compareTo

	public static void main( String []args ){//demo
		Data d=new Data();
		System.out.println("Oggi e' il "+d);
		System.out.println("Domani e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2000 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2008 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2009 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		if( Data.bisestile(2020) )
			System.out.println("Il 2020 e' un anno bisestile");

		System.out.println("Giorno di "+d+" = "+d.get( Data.Cosa.GIORNO ));

		System.out.println("Mese di "+d+" = "+d.get(Data.Cosa.MESE));

		System.out.println("Anno di "+d+" = "+d.get(Data.Cosa.ANNO));

	}//main
}//Data