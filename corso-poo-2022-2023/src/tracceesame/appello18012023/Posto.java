package tracceesame.appello18012023;

import java.util.Objects;

public class Posto {
	
	int fila;
	int sedile;
	
	public Posto(int fila, int sedile) {
		this.fila = fila;
		this.sedile = sedile;
	}

	@Override
	public int hashCode() {
		int h = 0;
		int prime = 31;
		h = h + prime * fila;
		h = h + prime * sedile;
		return h;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Posto)) return false;
		
		Posto other = (Posto) obj;
		return fila == other.fila && sedile == other.sedile;
	}
	
	@Override
	public String toString() {
		return "Posto [" +  fila + ", " + sedile +"]";
	}
	
}
