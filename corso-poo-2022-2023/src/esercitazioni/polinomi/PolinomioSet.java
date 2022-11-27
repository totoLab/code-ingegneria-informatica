package esercitazioni.polinomi;

import java.util.*;

public class PolinomioSet extends PolinomioAbstract{
	private Set<Monomio> monomi=new TreeSet<>();
	
	public int size() { return monomi.size(); }
	public Iterator<Monomio> iterator(){ return monomi.iterator(); }
	public PolinomioSet newInstancePolinomio() { return new PolinomioSet(); }
	
	public void add( Monomio m ) {
		if( m.getCoefficiente()==0 ) return;
		Iterator<Monomio> it=monomi.iterator();
		Monomio ms=null;
		while( it.hasNext() ) {
			Monomio mc=it.next();
			if( mc.equals(m) ) {
				it.remove();
				ms=mc;
				break;
			}
		}
		if( ms!=null ) {
			ms=ms.add(m);
			if( ms.getCoefficiente()!=0 ) monomi.add(ms);
		}
		else monomi.add(m);
	}//add
	
}//Polinomio
