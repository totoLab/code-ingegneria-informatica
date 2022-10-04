package poo.util;

public final class Mat{
	private Mat(){}

	private static double EPSILON=1.0E-10;

	public static int mcd( int x, int y ){
		if( x<=0 || y<=0 ) throw new IllegalArgumentException();
		return mcd_euclide(x,y);
	}//mcd

	private static int mcd_euclide( int x, int y ){
		if( y==0 ) return x;
		return mcd_euclide(y,x%y);
	}//mcd_euclide

	public static int mcm( int x, int y ){
		if( x<=0 || y<=0  ) throw new IllegalArgumentException();
		return (x*y)/mcd_euclide(x,y);
	}//mcm

	public static void setEpsilon( final double EPS ){
		if( EPS<=0 ) throw new IllegalArgumentException();
		EPSILON=EPS;
	}//setEpsilon

	public static double getEpsilon(){ return EPSILON; }

	public static boolean sufficientementeProssimi( double x, double y ){
		if( Math.abs(x-y)<=EPSILON ) return true;
		return false;
	}//sufficientementeProssimi

}//Mat