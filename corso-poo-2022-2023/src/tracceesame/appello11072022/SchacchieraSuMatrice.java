package tracceesame.appello11072022;

import java.util.*;

public class SchacchieraSuMatrice extends AbstractScacchiera {

	private PezzoDegliScacchi[][] scacchiera;
	
	protected SchacchieraSuMatrice(int dimensione) {
		super(dimensione);
		scacchiera = new PezzoDegliScacchi[this.dimensione][this.dimensione];
	}

	@Override
	public boolean posiziona(PezzoDegliScacchi pezzo) {
		int riga = pezzo.getPosizioneX();
		int colonna = pezzo.getPosizioneY();
		if (contenuto(riga, colonna) != null) return false;
		
		scacchiera[riga][colonna] = pezzo;
		return true;
	}

	@Override
	public Iterator<PezzoDegliScacchi> iterator() {
		return new MioIteratore();
	}
	
	private class MioIteratore implements Iterator<PezzoDegliScacchi> {

		private int count = 0;
		private boolean removable = false;
		
		@Override
		public boolean hasNext() {
			return count / dimensione() < dimensione() && count % dimensione() < dimensione;
		}
		
		@Override
		public PezzoDegliScacchi next() {
			removable = true;
			PezzoDegliScacchi p = scacchiera[count / dimensione()][count % dimensione()];
			
			do {
				count++;
			} while (hasNext() && scacchiera[count / dimensione()][count % dimensione()] == null);
			return p;
		}
		
		@Override
		public void remove() {
			if (!removable) throw new IllegalStateException();
			removable = false;
			scacchiera[count / dimensione()][count % dimensione()] = null;
		}
		
	}
	
	
}
