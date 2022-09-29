public class Razionale{
	private final int NUM, DEN;
	public Razionale( final int n, final int d ){
		int nu=n, de=d;
		if( de==0 )
			throw new IllegalArgumentException("Denominatore nullo.");
		if( de<0 ){
			nu=(-1)*nu; de=(-1)*de;
		}
		int MCD=mcd( Math.abs(nu), Math.abs(de) );
		nu=nu/MCD; de=de/MCD;
		NUM=nu; DEN=de;
	}
	public Razionale( Razionale r ){
		NUM=r.NUM; DEN=r.DEN;
	}

	public int getNum(){ return NUM; }
	public int getDen(){ return DEN; }

	public Razionale add( Razionale r ){
		int d1=DEN, d2=r.DEN;
		int mcm=mcm(d1,d2);
		int num=NUM*(mcm/DEN)+r.NUM*(mcm/r.DEN);
		return new Razionale( num, mcm );
	}//add

	public Razionale mul( int s ){
		return new Razionale( NUM*s, DEN );
	}//mul

	public Razionale mul( Razionale r ){
		return new Razionale( NUM*r.NUM, DEN*r.DEN );
	}//mul

	public String toString(){
		String s="";
		if( NUM==0 ) s=s+"0";
		else if( DEN==1 ) s=s+NUM;
		else{
			s=s+NUM+"/"+DEN;
		}
		return s;
	}

	private static int mcd( int x, int y ){
		if( y==0 ) return x;
		return mcd(y,x%y);
	}//mcd

	private static int mcm( int x, int y ){
		//PRE: x>0 & y>0
		return (x*y)/mcd(x,y);
	}//mcm

	public static void main( String[] args ){
		Razionale r1=new Razionale(4,12);
		Razionale r2=new Razionale(8,-10);
		System.out.println(r1+"+"+r2+"="+r1.add(r2));
		System.out.println(r1+"*"+r2+"="+r1.mul(r2));
		System.out.println(r1+"*5="+r1.mul(5));
	}//main

}//Razionale