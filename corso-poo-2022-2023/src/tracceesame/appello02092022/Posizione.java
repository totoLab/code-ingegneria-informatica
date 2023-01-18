package tracceesame.appello02092022;

import java.io.Serializable;

public class Posizione implements Serializable {
	
	int i, j;
	
	public Posizione(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public String toString() {
		return "Ombrellone {" + i + ", " + j + "}";
	}
	
}