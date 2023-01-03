package tracceesame.appello11072022;

import java.util.*;

public interface Scacchiera extends Iterable<PezzoDegliScacchi> {
	/* Aggiunge il pezzo sulla scacchiera restituendo TRUE.
	 * Restituisce FALSE se la cella non è vuota*/
	public boolean posiziona(PezzoDegliScacchi pezzo);
	
	/* Rimuove e restituisce il pezzo situato in posX,posY. 
	 * Restituisce NULL se la cella è vuota */
	default public PezzoDegliScacchi rimuovi(int posX, int posY) {
		Iterator<PezzoDegliScacchi> it = this.iterator();
		while (it.hasNext()) {
			PezzoDegliScacchi p = it.next();
			if (p.getPosizioneX() == posX || p.getPosizioneY() == posY) {
				 it.remove();
				return p;
			}
		}
		return null;
	}
	
	/* Restituisce il pezzo situato in posX,posY. 
	 * Restituisce NULL se la cella è vuota */
	default public PezzoDegliScacchi contenuto(int posX, int posY) {
		Iterator<PezzoDegliScacchi> it = this.iterator();
		while (it.hasNext()) {
			PezzoDegliScacchi p = it.next();
			if (p.getPosizioneX() == posX || p.getPosizioneY() == posY) {
				return p;
			}
		}
		return null;
	}
	
	/* Sposta il pezzo da sX,sY a dX,dY. Restituisce FALSE se la 
cella sorgente è vuota o quella di destinazione occupata */
	default public boolean muovi(int sX,int sY, int dX, int dY) {
		PezzoDegliScacchi sorgente = contenuto(sX, sY);
		PezzoDegliScacchi destinazione = contenuto(dX, dY);
		if (sorgente == null ||
				destinazione != null) {
			return false;
		}
		posiziona(new PezzoDegliScacchi(sorgente.getTipo(), sorgente.getColore(), dX, dY));
		rimuovi(sorgente.getPosizioneX(), sorgente.getPosizioneY());
		return true;
	}
	
	/* Controlla se sulla riga e sulla colonna di 
pX,pY ci sono altri pezzi sulla scacchiera */
	default boolean ciSonoPezziSullaRigaEColonna(int pX, int pY) { //MOLTO DIVERSO
		Iterator<PezzoDegliScacchi> it = this.iterator();
		while (it.hasNext()) {
			PezzoDegliScacchi p = it.next();
			if (p.getPosizioneX() == pX || p.getPosizioneY() == pY) {
				return true;
			}
		}
		return false;
	}

	/* Restituisce la dimensione dalla scacchiera (numero di
righe-colonne. Si ricorda che la scacchiera e' quadrata*/
	public int dimensione();
}
