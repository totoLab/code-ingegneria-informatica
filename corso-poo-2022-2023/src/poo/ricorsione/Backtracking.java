package poo.ricorsione;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Backtracking<P,S> {
	
	protected abstract boolean assegnabile( P p, S s );
	protected abstract void assegna( P ps, S s );
	protected abstract void deassegna( P ps, S s );
	protected abstract void scriviSoluzione( P p );
	
	private final P prossimoPuntoDiScelta( List<P> ps, P p ) {
		if( esisteSoluzione(p) ) throw new NoSuchElementException();
		int i=ps.indexOf(p);
		return ps.get(i+1);
	}//prossimoPuntoDiScelta
	
	protected boolean esisteSoluzione( P p ) {
		return false;
	}//esisteSoluzione
	
	protected boolean ultimaSoluzione( P p ) {
		return false; //cerca tutte le possibili soluzioni
	}//ultimaSoluzione
	
	//factory
	protected abstract List<P> puntiDiScelta();
	protected abstract Collection<S> scelte( P p );
	
	protected final void tentativo( List<P> ps, P p ) {
		Collection<S> sa=scelte(p); //scelte ammissibili per p
		for( S s: sa) {
			if( ultimaSoluzione(p) ) break;
			if( assegnabile(p,s) ) {
				assegna(p,s);
				if( esisteSoluzione(p) ) scriviSoluzione(p);
				else tentativo( ps, prossimoPuntoDiScelta(ps,p) );
				deassegna(p,s);
			}
		}
	}//tentativo
	
	protected abstract void risolvi();
	
}//Backtracking
