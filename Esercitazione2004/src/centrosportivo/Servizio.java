package centrosportivo;

public class Servizio {
	
	private int codice;
	private int postiTotali;
	private int postiDisponibili;
	private int turno; // 0: senior, 1: advanced, 2: junior
	private int costoSettimanale;
	
	public Servizio(int codice, int postiTotali, int postiDisponibili, int turno, int costoSettimanale) {
		this.codice = codice;
		this.postiTotali = postiTotali;
		this.postiDisponibili = postiDisponibili;
		this.turno = turno;
		this.costoSettimanale = costoSettimanale;
	}

	public int getCodice() {
		return codice;
	}

	public int getPostiTotali() {
		return postiTotali;
	}

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public int getTurno() {
		return turno;
	}

	public int getCostoSettimanale() {
		return costoSettimanale;
	}
	
	
}
