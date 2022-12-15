package esercitazioni.stringhe;

import java.util.*;

import javax.management.RuntimeErrorException;

public class StringaConcatenata extends StringaAbstract {

	private static class Nodo {
		char info;
		Nodo next;
	}
	
	private Nodo testa = null, coda = null;

	@Override
	public void append(char c) {
		Nodo n = new Nodo();
		n.info = c;
		if (testa == null) {
			testa = n;
			coda = n;
		} else {
			coda.next = n;
			coda = n;
		}
	}

	@Override
	public StringaIF copy() {
		StringaIF res = newInstanceStringa();
		res.append(this); // boh non ho mica capito 
		/*
		Nodo n = testa;
		while (n != null) {
			res.append(n.info);
		}*/
		return res;
	}

	@Override
	public void setCharAt(int pos, char c) { // inefficiente
		if (pos < 0 || this.length() < pos) throw new IllegalArgumentException();
		Nodo n = testa;

		for (int i = 0; i <= pos; i++) {
			n = n.next;
		}
		n.info = c;
	}

	@Override
	protected StringaIF newInstanceStringa() {
		return new StringaConcatenata();
	}
	
	@Override
	public Iterator<Character> iterator() {
		return new MioIteratore();
	}
	
	private class MioIteratore implements Iterator<Character> {

		Nodo prec = null, corr = null, succ = testa; // modello a "puntatore dietro il primo elemento"
		boolean rimuovibile = false;
		
		@Override
		public boolean hasNext() {
			return succ != null;
		}

		@Override
		public Character next() {
			if (!hasNext()) throw new RuntimeException();
			prec = corr;
			corr = succ;
			succ = succ.next;
			rimuovibile = true;
			return corr.info;
		}
		
		@Override
		public void remove() {
			if (!rimuovibile) throw new RuntimeException();
			rimuovibile = false;
			
			if (corr == testa) {
				testa = corr.next;
				if (testa == null) {
					coda = null;
				}
			} else {
				if (corr == coda) {
					coda = prec;
					prec.next = null;
				} else {
					prec.next = corr.next;
				}
			}
			corr = prec;
		}
	}

}
