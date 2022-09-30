package poo.app;
import poo.geometria.Punto;

public class MonteCarloPi{
	public static void main( String[] args ){
		final int N=1000000;
		int eventi=0;
		Punto origine=new Punto();
		for( int i=0; i<N; ++i ){
			//facciamo un esperimento casuale
			double x=Math.random()*2-1;
			double y=Math.random()*2-1;
			Punto p=new Punto(x,y);
			if( origine.distanza(p)<=1 ) eventi=eventi+1;
		}
		//eventi/N è circa uguale a pigreco/4
		double pi = (double)(4*eventi)/N;
		System.out.println("PI="+Math.PI+" pi="+pi);
	}

}