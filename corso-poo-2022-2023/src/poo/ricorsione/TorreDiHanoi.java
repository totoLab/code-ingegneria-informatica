package poo.ricorsione;

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
	
	public static void main(String[]args) {
		muovi(4, Pin.SX, Pin.CL, Pin.DX);	
	}
}
