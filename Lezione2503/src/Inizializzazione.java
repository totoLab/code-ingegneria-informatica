import java.util.ArrayList;

public class Inizializzazione {
	private static int intero = 0;
	private static ArrayList<Integer> a;
	
	static {
		for (int i = 1; i <= 10; i++) {
			a.add(i);
		}
		
	}
}
