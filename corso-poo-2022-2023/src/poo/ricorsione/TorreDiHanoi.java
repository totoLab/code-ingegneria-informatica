package poo.ricorsione;

import java.util.*;

public class TorreDiHanoi {

	private static enum Pin{SX,CL,DX}
	
	private static void spostaDisco(Pin da,Pin a) {
		System.out.println("Sposta  un disco da"+da+"a"+a);
	}
	
	public static void muovi(int n, Pin src,Pin aux,Pin dest) {
		if (n == 1) {
			spostaDisco(src, dest);
		} else {
			muovi(n - 1, src, dest, aux);
			spostaDisco(src, dest);
			muovi(n - 1, aux, src, dest);
		}
	}
	
	public static void muoviIterativo(int n, Pin src,Pin aux,Pin dest) {
		class AreaDati {
			int n;
			Pin src, aux, dest;
			AreaDati(int n, Pin src,Pin aux,Pin dest) {
				this.n = n; this.src = src; this.aux = aux; this.dest = dest;
			}
		}
		Stack<AreaDati> stack = new Stack<>();
		stack.push(new AreaDati(n, src, aux, dest));
		while (!stack.empty()) {
			AreaDati ad = stack.pop();
			if (ad.n == 1) {
				spostaDisco(ad.src, ad.dest);
			} else {
				stack.push(new AreaDati(ad.n - 1, ad.aux, ad.src, ad.dest));
				stack.push(new AreaDati(1, ad.src, ad.aux, ad.dest));
				stack.push(new AreaDati(ad.n - 1, ad.src, ad.dest, ad.aux));
			}
		}
	}
	
	public static void main(String[]args) {
		muovi(4, Pin.SX, Pin.CL, Pin.DX);	
	}
}
