package esercitazioni.polinomi;

import java.util.*;

public class PolinomioLL extends PolinomioAbstract{
	
	private LinkedList<Monomio> lista=new LinkedList<>();
	
	@Override
	public int size() { return lista.size(); } //ridefinito per ragioni di efficienza
	
	@Override
	public PolinomioLL newInstancePolinomio() { return new PolinomioLL(); } //covarianza del tipo di ritorno
	
	@Override
	public Iterator<Monomio> iterator(){ return lista.iterator(); }
	
	public void add( Monomio m ) {
		if( m.getCoefficiente()==0 ) return;
		ListIterator<Monomio> lit=lista.listIterator();
		boolean flag=false;
		while( lit.hasNext() && !flag ) {
			Monomio mc=lit.next();
			if( mc.equals(m) ) {
				Monomio nm=mc.add(m);
				if( nm.getCoefficiente()==0 ) { lit.remove(); }
				else { lit.set(nm); }
				flag=true;
			}
			else if( mc.compareTo(m)>0 ) { //GRADO CHE MANCA LO AGGIUNGO
				lit.previous(); lit.add(m); flag=true;
			}
		}//while
		if( !flag ) lit.add(m);
		
	}//add
	
}//PolonomioLL
