package poo.ricorsione;

import java.util.*;

public class Test {
    
    public static int fatt(int n) {
        if (n < 0) throw new IllegalArgumentException();
        return fattoriale(n);
    }

    private static int fattoriale(int n) {
        if (n == 0 || n == 1) return 1;
        return n * fattoriale(n - 1);
    }

    public static int potenza(int a, int n) {
        if (n < 0) throw new IllegalArgumentException();
        return pot(a, n);
    }

    private static int pot(int a, int n) { // n/2 iterazioni
        if (n == 0) return 1;
        if (n == 1) return a;
        int p = pot(a, n/2);
        p = p * p;
        if (n % 2 == 0) return p;
        return p * a;
    }

    private static int pot2(int a, int n) { // n iterazioni
        if (n == 0) return 1;
        if (n == 1) return a;
        return a * pot(a, n/2);
    }

    public int gauss(int n) {
        if (n == 1) return 1;
        return gauss(n - 1) + n;
    }

    public boolean palindroma(String s) {
        int len = s.length();
        if (len <= 1) return true;
        if (s.charAt(0) != s.charAt(len - 1)) return false;
        return palindroma(s.substring(1, len - 1));
    }

    public int max(int[] v) {
        if (v.length == 0) throw new NoSuchElementException();
        return massimo(v, 0, v.length - 1);
    }

    private int massimo(int[] v, int inf, int sup) {
        if (inf == sup) return v[inf];
        int med = (inf + sup) / 2;
        int m1 = massimo(v, inf, med);
        int m2 = massimo(v, med, sup);
        return (m1 >= m2) ? m1 : m2;
    }

    public static void permuta(int[] a, int i) {//i rappresenta l'indice da cui iniziano gli n-1 elementi da permutare
		if (i == a.length - 1) {
			System.out.println(Arrays.toString(a));
        } else {
			for (int j = i; j < a.length; j++) {
				int park = a[i];
				a[i] = a[j];
				a[j] = park;
				permuta(a, i + 1);
				park = a[i]; a[i] = a[j]; a[j] = park;
			}
		}
	}

}
