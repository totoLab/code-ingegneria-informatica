package src;

public class Lezione0403 {

	public static void main(String[] args) {
		//System.out.printf("%d %d", calcola(1, 2), calcola(3));
	}
	
	public static void casting() {
		long x = 3;
		int y = (int) x; // casting: tra tipi compatibili è possibile trattare una variabile come una del "casting type"
		System.out.println(y);
	}
	
	public static void division() {
		int x = 11;
		int y = 3;
		double intDiv = x / y;
		double div = x / (float) y;
		System.out.printf("x / y -> divisione intera: %f, divisione classica: %f.", intDiv, div);
	}
	
	public static int calcola(int a, int b) {
		return a+b;
	}
	
	public static long calcola(int a) {
		int power = 1;
		for (int i = 0; i < a; i++) {
			power *= a;
		}
		return (long) power;
	}
	
	public static void arrrrrrays() {
		int[] a = new int[3];
		a[0] = 1;
		a[1] = 42;
		a[2] = 21;
		
		int[] b = a; // copia del puntatore di a su b - aliases
		System.out.printf("%b\n", a == b);
		
		b = new int[] {1, 42, 21};
		System.out.printf("%b", a == b);
	}
	
	public static void stringssss() {
		String s1 = "ciao";
		String s2 = s1;
		
		System.out.println(s1 == s2);
		
		s2 = "ciao";
		System.out.println(s1 == s2);
	}
	
	public static void loops() {
		int i = 0;
		while (i < 5) {
			System.out.println(i);
			i++;
		}
		
		i = 0;
		for (; i < 5; i++) {
			System.out.println(i);
		}
		
		int[] a = new int[] {43, 8, 1, 0, 12};
		for (int n : a) {
			System.out.println(n);
		}
	}

}
