package esercitazioni.polinomi;

import java.util.*;

public interface Polinomio extends Iterable<Monomio> {
	
	final int c = 0;
	
	default int size() {
		int size = 0;
		for (Iterator<Monomio> it = this.iterator(); it.hasNext(); it.next()) {
			size++;
		}
		return size;
	}
	
	default Polinomio add(Polinomio p) {
		Polinomio somma = newInstancePolinomio();
		
		for (Monomio m: this) {
			somma.add(m);
		}
		
		for (Monomio m: p) {
			somma.add(m);
		}
		return somma;
	}
	
	default Polinomio mul( Polinomio p ) {
		Polinomio prodotto = newInstancePolinomio();
		for (Monomio m: this) {
			prodotto = prodotto.add(p.mul(m));
		}
		return prodotto;
	}

	default Polinomio mul(Monomio m) {
		Polinomio prodotto = newInstancePolinomio();
		
		for (Monomio m1 : this) {
			prodotto.add(m1.mul(m));
		}
		
		return prodotto;
	}
	
	default Polinomio mul(int coefficiente) {
		Polinomio prodotto = newInstancePolinomio();
		
		for (Monomio m1 : this) {
			prodotto.add(m1.mul(coefficiente));
		}
		
		return prodotto;
	}

	default Polinomio derivata() {
		Polinomio d = newInstancePolinomio();
		for( Monomio m: this ) {
			if(m.getGrado() > 0) {
				d.add( new Monomio( m.getCoefficiente()*m.getGrado(), m.getGrado()-1 ) );
			}
		}
		return d;
	}

	default double valore( double x ) {
		double v=0.0D;
		for( Monomio m: this )
			v=v+m.getCoefficiente()*Math.pow(x, m.getGrado());
		return v;
	}
	
	default void clear() {
		Iterator<Monomio> it=iterator(); 
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	void add( Monomio m );
	Polinomio newInstancePolinomio();	
	void save(String path);
	void restore(String path);
}
