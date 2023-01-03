package tracceesame.appello11072022;

import java.util.*;

public class ScacchieraSuLinkedList extends AbstractScacchiera {

	private List<PezzoDegliScacchi> scacchiera;
	
	protected ScacchieraSuLinkedList(int dimensione) {
		super(dimensione);
		this.scacchiera = new LinkedList<>();
	}

	@Override
	public boolean posiziona(PezzoDegliScacchi pezzo) {
		int riga = pezzo.getPosizioneX();
		int colonna = pezzo.getPosizioneY();
		if (contenuto(riga, colonna) != null) return false;
		
		scacchiera.add(pezzo);
		return true;
	}

	@Override
	public Iterator<PezzoDegliScacchi> iterator() {
		return scacchiera.iterator();
	}

}
