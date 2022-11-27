package poo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Funzioni{
	
	public enum Type{lessicografico, lunghezza};
	Type t;
	
	public Funzioni(Type t) {
		this.t=t;
	}
	public static int primoConfronto(String s1, String s2) {
		if (s1.length()<s2.length()) return -1;
		if (s1.length()>s2.length()) return +1;
		return s1.compareTo(s2);
	}
	public static int secondoConfronto(String s1, String s2) {
		return s1.compareTo(s2);
	}
	public int terzoConfronto(String s1,String s2) {
		if (t==Type.lessicografico)
			return primoConfronto(s1, s2);
		else
			return secondoConfronto(s1, s2);
	}
}

public class InterfacceFunzionali {
	
	public static<T> void copia(List<? extends T> source, List<? super T> dest){
		boolean finito=false;
		while(!finito) {
			T read=source.remove(0);
			dest.add(read);
			finito=source.isEmpty();
		}
	}
	
	public static<T> void aggiungi(List<? super T> l, T val) {
		l.add(val);
	}
	
	public static<T> T leggiPrimo(List<? extends T> l) {
		T val = l.get(0);
		return val;
	}
	
	public static void main(String[] args) {

		List<String> list = 	Arrays.asList("casa","albero","mandarino","pelle","collana","statua");

		
		Comparator<String> c0 = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length()<s2.length()) return -1;
				if (s1.length()>s2.length()) return +1;
				return s1.compareTo(s2);
			}
		};
		
		Comparator<String> c1 = (s1,s2)->{
				if (s1.length()<s2.length()) return -1;
				if (s1.length()>s2.length()) return +1;
				return s1.compareTo(s2);
			};
			
		Collections.sort(list,c1);
		System.out.println(list);
		
		Comparator<String> c2 = Funzioni::secondoConfronto;
		Collections.sort(list,c2);
		System.out.println(list);
		
		Funzioni f=new Funzioni(Funzioni.Type.lessicografico);
		Comparator<String> c3 = f::terzoConfronto;
		Collections.sort(list,c3);
		System.out.println(list);
	}
}
