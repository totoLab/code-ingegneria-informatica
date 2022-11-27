package esercitazioni.stringhe;

import java.util.*;

public class StringaConcatenata extends StringaAbstract{
	private static class Nodo{
		char info;
		Nodo next;
	}
	private Nodo testa=null, coda=null;

	public Iterator<Character> iterator(){
		return new Iteratore();
	}
	
	protected StringaIF newInstanceStringa(){
		return new StringaConcatenata();
	}

	public void setCharAt( int pos, char c ){
		if( pos<0 || pos>=this.length() )
			throw new IndexOutOfBoundsException();
		int i=0;
		Nodo n=testa;
		for( ; i<pos; i++, n=n.next );
		n.info=c;
	}
	
	public void append( char c ){
		Nodo n=new Nodo(); 
		n.info=c; 
		n.next=null;
		if( testa==null ) 
			testa=n;
		else 
			coda.next=n;
		coda=n;
	}
	
	public StringaIF copy(){
		StringaIF sc=new StringaConcatenata();
		sc.append( this );
		return sc;
	}

	//i metodi che seguono sono ridefiniti per scopi dimostrativi
	public StringaIF substring( int da ){
		if( da<0 || da>=this.length() )
			throw new IndexOutOfBoundsException();
		StringaIF sub=new StringaConcatenata();
		Nodo cor=testa;
		int i=0;
		for( ; i<this.length(); i++, cor=cor.next ){
			if( i>=da ) sub.append( cor.info );
		}
		return sub;
	}

	public StringaIF substring( int da, int a ){
		if( da<0 || da>=this.length() || a<0 || a>=this.length() || da>a )
			throw new IndexOutOfBoundsException();
		StringaIF sub=new StringaConcatenata();
		Nodo cor=testa;
		int i=0;
		for( ; i<this.length(); i++, cor=cor.next ){
			if( i>=da && i<a ) sub.append( cor.info );
		}
		return sub;
	}

	private class Iteratore implements Iterator<Character>{

		Nodo pre=null, cor=null, suc=testa;
		boolean rimovibile = false; 
		@Override
		public boolean hasNext() {
			return suc!=null;
		}

		@Override
		public Character next() {
			if(!hasNext()) throw new NoSuchElementException();
			pre=cor;
			cor=suc;
			suc=suc.next;
			rimovibile=true;
			return cor.info;
		}
		
		public void remove() {
			if(!rimovibile) throw new IllegalStateException();
			rimovibile=false;
			if(cor==testa) {
				testa=testa.next;
				cor.next=null;
				cor=pre;
				if(testa==null)coda=null;
			} else {
				if(cor==coda) {
					pre.next=null;
					coda=pre;
				} else {
					cor.next=null;
					pre.next=suc;
				}
				cor=pre;
			}
		}
		
	}

}//StringaConcatenata
