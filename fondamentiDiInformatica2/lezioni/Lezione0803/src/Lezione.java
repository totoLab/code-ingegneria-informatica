import terminale.*;

public class Lezione {

	public static void main(String[] args) {
		int sommaImportata = Terminale.somma(1, 2);
		
		usiamoLoSwitch(sommaImportata);
		stringheImmutabili();
	}
	
	public static void usiamoLoSwitch(int n) {
		
		switch (n) {
			case 1:
				System.out.println("ciao1");
				break;
			case 2:
			case 3:
				System.out.println("ciao2 o ciao3");
				break;
			default:
				System.out.println("va mmazzati");
		}	
	}
	
	public static void stringheImmutabili() {
		String s = "abc";
		
		s.concat("def");
		System.out.println(s);
		
		s = s.concat("def");
		System.out.println(s);
	}

}
