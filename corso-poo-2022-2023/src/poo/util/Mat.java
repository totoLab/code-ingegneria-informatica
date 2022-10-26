package poo.util;

public class Mat {
	
	
	private Mat() {}
	
	public static int mcd(int n, int m) {
		if (n<0 || m<0)
			throw new IllegalArgumentException("Parametri errati");
		if (m==0) return n;
		return mcd(m, n%m);
	}
	
	public static int mcm(int n, int m) {
		if (n<0 || m<0)
			throw new IllegalArgumentException("Parametri errati");
		return (n*m)/mcd(n,m);
	}
	
	public static boolean sufficientementeProssimi(double x1, double x2) {
		if ( Math.abs(x1-x2)<=EPSILON )
			return true;
		return false;
	}
	
	private static double EPSILON=0.000000001; 
	
	public static void setEpsilon(double eps) {
		EPSILON=eps;
	}
	
	public static double getEpsolon() {
		return EPSILON;
	}

}
