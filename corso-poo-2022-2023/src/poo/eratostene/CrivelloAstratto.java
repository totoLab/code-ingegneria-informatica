package poo.eratostene;

import java.util.*;

public abstract class CrivelloAstratto implements Crivello {
	
	public int size() {
		int c = 0;
		for (int x : this) {
			c++;
		}
		return c;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int c = 0;
		for (int x : this) {
			sb.append(x).append(" ");
		}
		return sb.toString();
	}
	
	
}
