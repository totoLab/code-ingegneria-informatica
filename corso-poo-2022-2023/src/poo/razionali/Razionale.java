package poo.razionali;
import poo.util.Mat;

public class Razionale{
	private final int NUM, DEN;

	private static int conta=0;

	public Razionale( final int n, final int d ){
		int nu=n, de=d;
		if( de==0 )
			throw new IllegalArgumentException("Denominatore nullo.");
		if( de<0 ){
			nu=(-1)*nu; de=(-1)*de;
		}
		int MCD=Mat.mcd( Math.abs(nu), Math.abs(de) );
		nu=nu/MCD; de=de/MCD;
		NUM=nu; DEN=de;
		conta++;
	}
	public Razionale( Razionale r ){
		NUM=r.NUM; DEN=r.DEN;
		conta++;
	}

	public int getNum(){ return NUM; }
	public int getDen(){ return DEN; }

	public Razionale add( Razionale r ){
		int d1=DEN, d2=r.DEN;
		int mcm=Mat.mcm(d1,d2);
		int num=NUM*(mcm/DEN)+r.NUM*(mcm/r.DEN);
		return new Razionale( num, mcm );
	}//add

	public Razionale mul( int s ){
		return new Razionale( NUM*s, DEN );
	}//mul

	public Razionale mul( Razionale r ){
		return new Razionale( NUM*r.NUM, DEN*r.DEN );
	}//mul

	public Razionale div( Razionale r ){
		return new Razionale( this.NUM*r.DEN, this.DEN*r.NUM );
	}

	public String toString(){
		String s="";
		if( NUM==0 ) s=s+"0";
		else if( DEN==1 ) s=s+NUM;
		else{
			s=s+NUM+"/"+DEN;
		}
		return s;
	}

	protected void finalize(){
		conta--;
	}//finalize

	public static int razionaliEsistenti(){
		return conta;
	}//razionaliEsistenti

	public static void main( String[] args ){
		//Sviluppare 4/12-8/10+2/8*15/9
		System.out.println("Valutazione di: 4/12-8/10+2/8*15/9");
		Razionale r1=new Razionale(4,12);
		Razionale r2=new Razionale(-8,10);
		Razionale r3=new Razionale(2,8);
		Razionale r4=new Razionale(15,6);
		Razionale r=r1.add(r2.add(r3.mul(r4)));
		System.out.println(r1+"+"+r2+"+"+r3+"*"+r4+"="+r);
		r1=null;
		System.out.println("Razionali esistenti="+r.razionaliEsistenti());
	}//main

}//Razionale