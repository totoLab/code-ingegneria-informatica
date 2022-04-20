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
}
