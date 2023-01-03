package tracceesame.appello11072022;

import java.util.Objects;

public class PezzoDegliScacchi {

	private Tipo tipo;
	private Colore colore;
	private int posizioneX;
	private int posizioneY;
	
	public enum Tipo {re, regina, torre, alfiere, cavallo, pedone};
	public enum Colore {bianco, nero};
	
	public PezzoDegliScacchi(Tipo tipo, Colore colore, int posizioneX, int posizioneY) {
		this.tipo = tipo;
		this.colore = colore;
		this.posizioneX = posizioneX;
		this.posizioneY = posizioneY;
	}
	
	public PezzoDegliScacchi(PezzoDegliScacchi p) {
		this(p.getTipo(), p.getColore(), p.getPosizioneX(), p.getPosizioneY());
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public Colore getColore() {
		return colore;
	}
	
	public int getPosizioneX() {
		return posizioneX;
	}
	
	public int getPosizioneY() {
		return posizioneY;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(colore, posizioneX, posizioneY, tipo);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof PezzoDegliScacchi)) return false;
		
		PezzoDegliScacchi p = (PezzoDegliScacchi) o;
		return this.tipo == p.tipo && this.colore == p.colore &&
				this.posizioneX == p.posizioneX && this.posizioneY == p.posizioneY;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(tipo);
		sb.append(" del ");
		sb.append(colore);
		sb.append(" in posizione ");
		sb.append(posizioneX);
		sb.append(", ");
		sb.append(posizioneY);
		return sb.toString();
	}

}
