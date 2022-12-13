package esercitazioni.lambdas;

import java.util.*;
import java.util.stream.Collectors;
public class TestLamdaExprProf {

	/*
	Predicate<T>
	boolean test(T t);
	
	Consumer<T>
	void accept(T t);
	
	Supplier<T>
	T get();
	*/
	
	public static List<String> reverseEachElem2(List<String> l){
		if(l==null || l.isEmpty()) {
			return null;//si può sollevare l'eccezione
		}
		List<String> result = new LinkedList<>();
		for(String s:l) {
			result.add(reverse2(s));
		}
		return result;
	}
	
	public static String reverse2(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=s.length()-1; i>=0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	public List<String> reverseEachElem(List<String> l){
		if(l==null || l.isEmpty()) {
			return null;//si può sollevare l'eccezione
		}
		List<String> result = new LinkedList<>();
		for(String s:l) {
			result.add(reverse(s));
		}
		return result;
	}
	
	public List<String> lambdaReverse(List<String> l){
		return l.stream().map(this::reverse).collect(Collectors.toList());
	}

	
	
	public String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=s.length()-1; i>=0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	
	public void test(List<String> l) {
		reverseEachElem(l);
	}
	
	public List<String> filteredList(List<String> l){
		if(l==null && l.isEmpty()) {
			return null; //SOLLEVARE ECCEZIONE
		}
		List<String> result = new LinkedList<>();
		for(String s:l) {
			if(filterElem(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	public boolean filterElem(String s) {
		return !s.toUpperCase().contains("E");
	}
	
	
	public boolean findString(List<String> l, String s) {
		if(l==null || l.isEmpty())
		{
			return false;
		}
		for(String str:l) {
			if(str.equals(s)) {
				return true;
			}
		}
		return false;
	}
	

	
	public void printList(List<String> l) {
		if(l==null || l.isEmpty()) {
			return;
		}
		for(String s:l) {
			System.out.println(s);
		}
	}
	
	public void printListLambda(List<String> l) {
		l.stream().forEach((String elem)->{System.out.println(elem);});
	}
	
	public boolean findStringLambda(List<String> l, String s) {
		return l.stream().anyMatch((String elem) ->{return elem.equals(s);});
	}
	
	public List<String> filteredLambda(List<String> l){
		return l.stream().filter(
				(String elem) -> {return !elem.toUpperCase().contains("E");}
				//this::filterElem
				).collect(Collectors.toList());
	}
	
	public List<Integer> lambdaMap(List<String> l){
		return l.stream().map(elem -> {
			return elem.length();
			}).collect(Collectors.toList());
	}
	
	public static void main(String...args) {
		List<String> l1= new LinkedList<>();
		l1.add("TEST");
		l1.add("ABC");
		
		TestLamdaExprProf test = new TestLamdaExprProf();
		System.out.println(test.filteredList(l1));
		/*System.out.println(test.lambdaReverse(l1));
		System.out.println(test.lambdaMap(l1));//reverseEachElem(l1));
		System.out.println(TestLamdaExpr.reverseEachElem2(l1));*/
		
		
	}
	
}
