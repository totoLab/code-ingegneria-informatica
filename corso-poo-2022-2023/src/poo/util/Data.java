package poo.util;

import java.util.*;

public class Data {
	
	public enum Tipologia {Giorno, Mese, Anno}
	
	private final int G,M,A;
	
	//public static final int GIORNO=0, MESE=1, ANNO=2;
	
	public Data() {
		GregorianCalendar gc = new GregorianCalendar();
		G=gc.get(GregorianCalendar.DAY_OF_MONTH);
		M=gc.get(GregorianCalendar.MONTH)+1;
		A=gc.get(GregorianCalendar.YEAR);
	}
	public Data(int g, int m, int a) {
		if (m<1 || m>12 || g<1 || g>durataMese(m,a) || a<0)
			throw new IllegalArgumentException();
		G=g;A=a;M=m;
	}
	public Data(Data d) {
		if (d==null)
			throw new IllegalArgumentException();
		G=d.G;
		A=d.A;
		M=d.M;
	}
	public static boolean bisestile(int a) {
		if (a<0)
			throw new IllegalArgumentException();
		if(a%4!=0) return false;
		if(a%100==0 && a%400!=0) return false;
		return true;
	}
	public static int durataMese(int m, int a) {
		if (m<1 || m>12 || a<0)
			throw new IllegalArgumentException();
		int durata;
		switch(m) {
		case 1:case 3: case 5: case 7: case 8: case 10: case 12: durata=31; break;
		case 2: durata=bisestile(a)?29:28; break;
		default: durata=30;
		}
		return durata;
	}
	
	public int get(Tipologia cosa) {
		switch(cosa) {
		case Giorno: return G;
		case Mese: return M;
		case Anno: return A;
		default: return -1;
		}
	}
		
	public Data giornoDopo() {
		int durata = durataMese(M, A);
		int g1, m1, a1;
		if (G==durata) {
			g1=1;
			if (M==12) {m1=1; a1=A+1;}
			else {m1=M+1; a1=A;}
		}else {g1=G+1; m1=M; a1=A;}
		return new Data(g1,m1,a1);
	}
	
	
	

}
