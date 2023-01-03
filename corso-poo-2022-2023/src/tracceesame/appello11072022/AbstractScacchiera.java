package tracceesame.appello11072022;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractScacchiera implements Scacchiera {

	protected int dimensione;
	
	protected AbstractScacchiera(int dimensione) {
		this.dimensione = dimensione;
	}
	
	public int dimensione() {
		return dimensione;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Scacchiera di dimensione ");
		sb.append(dimensione);
		sb.append(" con il seguente FEN:\n");
		
		Iterator<PezzoDegliScacchi> it = this.iterator();
		while (it.hasNext()) {
			PezzoDegliScacchi p = it.next();
			sb.append(p);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dimensione);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof Scacchiera)) return false;
		
		Scacchiera s = (Scacchiera) o;
		if (this.dimensione() != s.dimensione()) return false;
		return stessoFEN(this, s);
	}
	
	private boolean stessoFEN(Scacchiera s1, Scacchiera s2) {
		int cont1 = 0, cont2 = 0;
		for (PezzoDegliScacchi p : s1) {
			cont1++;
			if (
					!(s1.contenuto(p.getPosizioneX(), p.getPosizioneY())
						.equals(
							s2.contenuto(p.getPosizioneX(), p.getPosizioneY())
					))
				) return false;
		}
		for (PezzoDegliScacchi p : s1) cont2++;
		return cont1 == cont2;
	}
	
}
