package poo;

import java.util.*;

class MioComparator implements Comparator<String> {
	public int compare(String s1, String s2) {
		if (s1.length() < s2.length()) return -1;
		if (s1.length() > s2.length()) return 1; 
		return s1.compareTo(s2); // a parità di lunghezza usa il confronto di default
	}
}

class Funzioni {
	public static int primoTipoDiConfronto(String s1, String s2) {
		return s1.compareTo(s2);
	}
	
	public static int secondoTipoDiConfronto(String s1, String s2) {
		if (s1.length() < s2.length()) return -1;
		if (s1.length() > s2.length()) return 1; 
		return s1.compareTo(s2);
	}
}

class InterfacceFunzionali {

	public static void main(String[] args) {
		
		// #1
		List<String> ls = Arrays.asList("casa", "tetto", "albero", "cassaforte");
		Collections.sort(ls);

		// #2
		Comparator<String> cc = new MioComparator();
		Collections.sort(ls, cc);
		
		// #3
		Comparator<String> cs = new Comparator<String>() {
			public int compare(String s1, String s2) {
				if (s1.length() < s2.length()) return -1;
				if (s1.length() > s2.length()) return 1; 
				return s1.compareTo(s2);
			}
		};
		Collections.sort(ls, cs);
		
		// #4 - lambda expression
		Comparator<String> cl = (s1, s2) -> {
			if (s1.length() < s2.length()) return -1;
			if (s1.length() > s2.length()) return 1; 
			return s1.compareTo(s2);
		};
		Collections.sort(ls, cl);
		
		// #5
		Comparator<String> cf = Funzioni::primoTipoDiConfronto;
		Collections.sort(ls, cf);

		Funzioni ff = new Funzioni();
		
	}
	
public class Test {
	
	public static void main(String[] args) {
		
	}
	
}

}
