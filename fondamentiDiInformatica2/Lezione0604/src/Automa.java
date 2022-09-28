import terminale.Terminale;

public class Automa {
	
	private int stato;
	
	public Automa () {
		this.stato = 0;
	}
	
	public boolean riconosci(String s) {
		this.stato = 0;
		for (int i = 0; i < s.length(); i++) {
			char simbolo = s.charAt(i);
			transizione(simbolo);
			Terminale.stampa("Visto il simbolo " + simbolo + " passo allo stato " + stato);
		}
		return stato == 5;
	}
	
	private void transizione(char simbolo) {
		switch (this.stato) {
			case 0:
				if (simbolo == 'a') {
					stato = 1;	
				} else {
					stato = 2;
				}
				break;
			case 1:
				if (simbolo == 'b') {
					stato = 3;	
				} else {
					stato = 2;
				}
				break;
			case 2:
				break;
			case 3:
				if (simbolo == 'c') {
					stato = 4;	
				} else if (simbolo != 'b' && simbolo != 'c') {
					stato = 2;
				}
				break;
			case 4:
				if (simbolo == 'c') {
					stato = 5;	
				} else {
					stato = 2;
				}
				break;
			case 5:
				stato = 2;
				break;
		}
	}
	
}

