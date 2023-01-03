package esercitazioni.lambdas;

import java.util.function.*;
import java.util.*;
import java.util.stream.*;

public class TestLambdaExpr {
	/*
	Predicate<T> 
	boolean test(T t);
	
	Consumer<T>
	void accept(T t);
	
	Supplier<T>
	T get();
	*/
	
	
	// esercizio: data una lista di stringhe, return lista di stringhe con stringa-i = stringa-i.reversed()
	public List<String> reverseEachString(List<String> l) {
		if (l == null || l.isEmpty()) throw new IllegalArgumentException();
	
		List<String> ret = new LinkedList<>();
		for (String s : l) {
			ret.add(reverse(s));
		}
		return ret;
	}
	
	public String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	public List<String> lambdaReverse(List<String> l) {
		return l.stream().map(this::reverse).collect(Collectors.toList());
	}
	
	public List<String> lambdaFilter(List<String> l) {
		return l.stream().filter(this::filterElem).collect(Collectors.toList());
	}
	
	public boolean filterElem(String s) {
		return !s.toUpperCase().contains("E");
	}
	
	public boolean lambdaFind(String s, List<String> l) {
		return l.stream().anyMatch(elem -> {
			return elem.equals(s);
		});
	}
	
	public void lambdaPrint(List<String> l) {
		l.stream().forEach(elem -> {
			System.out.println(elem);
		});
	}
	
}