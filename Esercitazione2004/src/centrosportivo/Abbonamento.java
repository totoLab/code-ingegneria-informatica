package centrosportivo;

public class Abbonamento {
	
	private int codiceServizio;
	private boolean[][] mesiSettimane;
	
	private static final String[] mesi = {"gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"};
	
	public Abbonamento(int codiceServizio, boolean[][] mesiSettimane) {
		this.codiceServizio = codiceServizio;
		this.mesiSettimane = new boolean[12][4];
		for(int i = 0; i < 12; i++) {
			for (int j = 0; j < 4; j++) {
				this.mesiSettimane[i][j] = mesiSettimane[i][j];
			}
		}
	}
	
	public int getCodiceServizio() {
		return this.codiceServizio;
	}
	
	public boolean[][] getMesiSettimane() {
		return this.mesiSettimane;
	}
	
	public static int getIndiceMese(String mese) {
		int i = -1;
		for (int k = 0; k < mesi.length; k++) {
			if (mese.equalsIgnoreCase(mesi[k])) {
				i = k;
				break;
			}
		}
		return i;
	}

	public int getSettimaneEffettive(String mese) {
		int i = getIndiceMese(mese);
		int cont = 0;
		for (int j = 0; j < 4; j++) {
			if (mesiSettimane[i][j]) {
				cont++;
			}
		}
		return cont;
	}
}
