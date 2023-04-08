package threadLifeCicle;

import java.util.*;
import java.util.stream.IntStream;
import java.text.*;

//esercizio 2.2 prodotto scalare di due array di dimensione n
// utilizzare m oggetti thread istanza di una classe ProdottoScalare
// ciascun oggetto calcola il ps su una porzione di lunghezza n/m dei due array 
// assumere che n sia multiplo di m
	
// main inizializza gli array e stampa il risultato
// m è una costante definita nel programma

public class ProdottoScalare extends Thread {
	
	private int[] a1, a2;
	private int from, to, result;
	
	public ProdottoScalare(int[] a1, int[] a2, int from, int to) {
		this.result = 0;
		this.a1 = a1;
		this.a2 = a2;
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void run() {
		for (int i = from; i <= to; i++) {
			result += a1[i] * a2[i];
		}
		// System.out.println("Done " + this.getId());
	}
	
	public int getResult() throws InterruptedException {
		this.join();
		return result;
	}
	
	public static int ps(int[] v1, int[] v2, int m) {
		int n = (v1.length == v2.length) ? v1.length : -1;
		if (n < 0 || n % m != 0) throw new IllegalStateException();

		ProdottoScalare[] prodottoScalare = new ProdottoScalare[m];
		
		int increment = (int) n / m;
		for (int i = 0; i < prodottoScalare.length; i++) {
			int from = increment * i;
			int to = from + increment - 1;
			prodottoScalare[i] = new ProdottoScalare(v1, v2, from, to);
			prodottoScalare[i].start();
		}

		int ps = 0;
		for (int i = 0; i < prodottoScalare.length; i++) {
			try {
				ps += prodottoScalare[i].getResult();
			} catch (InterruptedException e) {
				System.err.print("Error on Thread " + prodottoScalare[i].getId() + " indexed at " + i);
				e.printStackTrace();
			}
		}
		return ps;
	}
	
	public static void main(String[] args) {
		final int[] v1 = IntStream.generate(() -> new Random().nextInt(100)).limit(100).toArray();
		final int[] v2 = IntStream.generate(() -> new Random().nextInt(100)).limit(100).toArray();
		// final int m = 2; // number of threads
		
		for (int m = 1; m < v2.length; m++) { // testing multiple thread configurations
			if (v1.length % m == 0) {
				System.out.println("Numero Threads = " + m);
				testProdottoScalare(v1, v2, m);
				System.out.println();
			}
		}
	}
	
	private static void testProdottoScalare(int[] v1, int[] v2, int m) {
		long start = System.nanoTime();
		int psC = ProdottoScalare.ps(v1, v2, m);
		long end = System.nanoTime();
		System.out.println("Prodotto scalare concurrent: " + psC);
		System.out.println("Durata test: " + convertTime(start, end) + "s");
		
		start = System.nanoTime();
		int ps = ps(v1, v2);
		end = System.nanoTime();
		System.out.println("Prodotto scalare: " + ps);
		System.out.println("Durata test: " + convertTime(start, end) + "s");
	}

	private static int ps(int[] v1, int[] v2) {
		int result = 0;
		for (int i = 0; i < v1.length; i++) {
			result += v1[i] * v2[i];
		}
		return result;
	}
	
	private static String convertTime(long start, long end) {
		double time = (end - start) / Math.pow(10, 9);
		DecimalFormat df = new DecimalFormat("#.########");
		return df.format(time);
	}	
	
}
