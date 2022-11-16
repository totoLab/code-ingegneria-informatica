package esercitazioni.polinomi;

import java.util.*;

public interface Polinomio extends Iterable<Monomio> {

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
		
		void add(Monomio m);
		
		default Polinomio mul(Polinomio p) {
			Polinomio prodotto = newInstancePolinomio();
			
			for (Monomio m1: this) {
				for (Monomio m2: p) {
					prodotto.add(m1.mul(m2));
				}
			}
			
			return prodotto;
		}
		
		Polinomio mul(Monomio m);
		Polinomio mul(int coeff);
		Polinomio derivata();
		int valore(int x);
		int grado();
		Polinomio newInstancePolinomio();
}
