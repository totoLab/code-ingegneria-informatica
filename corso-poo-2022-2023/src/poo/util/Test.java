package poo.util;

import java.util.Iterator;

public class Test {
	
	public static void main(String[] args) {
		Vector<String> test = new ArrayVector<String>(10);
		test.add("prima");
		String s = test.get(0);
		Iterator<String> iter = test.iterator();
		String s1 = iter.next();
		System.out.println(s1);
		
	}

}
