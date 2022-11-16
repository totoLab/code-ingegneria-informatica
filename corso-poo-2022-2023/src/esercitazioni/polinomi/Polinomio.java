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
		
		default Polinomio mul(Polinomio p) {
			Polinomio prodotto = newInstancePolinomio();
			
			for (Monomio m1: this) {
				for (Monomio m2: p) {
					prodotto.add(m1.mul(m2));
				}
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
			Polinomio derivata = newInstancePolinomio();
			
			for (Monomio m : this) {
				derivata.add(m.derivata());
			}
			
			return derivata;
		}
		
		default int grado() {
			int grado = 0;
			for (Monomio m : this) {
				grado = m.getGrado(); // ipotizzando polinomio ordinato per gradi decrescenti (il primo monomio è quello con il grado massimo)
				break;
			}
			return grado;
		}
		
		Polinomio add(Monomio m);
		int valore(int x);
		Polinomio newInstancePolinomio();
}
