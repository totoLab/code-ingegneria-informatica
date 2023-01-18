package tracceesame.appello18012023;

import java.util.*;

abstract class AereoAbstract implements Aereo {
	
	protected int file, sedili;
	
	public AereoAbstract(int file, int sedili) {
		this.file = file;
		this.sedili = sedili;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("File: ");
		sb.append(file);
		sb.append(", sedili per fila: ");
		sb.append(sedili);
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		int h = 0;
		int prime = 31;
		h = h + prime * file;
		h = h + prime * sedili;
		return h;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Posto)) return false;
		
		Posto other = (Posto) obj;
		return file == other.fila && sedili == other.sedile;
	}

	@Override
	public List<Posto> postiAereo() {
		List<Posto> posti = new ArrayList<>();
		for (int i = 0; i < file; i++) {
			for (int j = 0; j < sedili; j++) {
				posti.add(new Posto(i, j));
			}
		}
		return posti;
	}
	
}
