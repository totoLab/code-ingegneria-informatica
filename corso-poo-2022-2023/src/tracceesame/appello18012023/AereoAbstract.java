package tracceesame.appello18012023;

import java.util.*;

abstract class AereoAbstract implements Aereo {
	
	protected int file, sedili;
	
	public AereoAbstract(int file, int sedili) {
		this.file = file;
		this.sedili = sedili;
	}
	
	public int getFile() {
		return file;
	}
	
	public int getSedili() {
		return sedili;
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
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Aereo)) return false;
		
		Aereo other = (Aereo) obj;
		if (!(this.postiAereo().equals(other.postiAereo()))) return false;
		for (Posto p : postiAereo()) {
			String p1 = this.passeggeroDelPosto(p), p2 = other.passeggeroDelPosto(p);
			if (!(p1.equals(p2))) return false;
			if (!(this.bagaglioDelPasseggero(p1)
					.equals(other.bagaglioDelPasseggero(p1)))) return false;
		}
		return true;
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
