package esercitazioni.stringhe;

import java.util.*;

public class StringaArray extends StringaAbstract{

	private char[] contenuto;
	private int n, size;

	public StringaArray(){
		this(50);
	}

	public StringaArray( int n ){
		this.n=n;
		contenuto=new char[n]; size=0;
	}

	public int length(){
		return size;
	}

	public Iterator<Character> iterator(){
		return new Iteratore();
	}
	
	protected StringaIF newInstanceStringa(){
		return new StringaArray();
	}

	public void setCharAt( int pos, char c ){
		if( pos<0 || pos>=this.length() )
			throw new IndexOutOfBoundsException();
		contenuto[pos]=c;
	}
	
	public void append( char c ){
		if( size==n ){
			char []nuovo=new char[n*2];
			System.arraycopy( contenuto, 0, nuovo, 0, n );
			n*=2;
			contenuto=nuovo;
		}
		contenuto[size]=c; size++;
	}

	public StringaIF copy(){
		StringaIF sc=new StringaArray();
		sc.append( this );
		return sc;
	}

	private class Iteratore implements Iterator<Character>{
		
		int suc = 0;
		boolean rimovibile=false;
		
		public boolean hasNext(){
			return suc!=size;
		}

		public Character next(){
			if( !hasNext() ) throw new NoSuchElementException();
			rimovibile=true;
			suc++;
			return contenuto[suc-1];
		}
		
		public void remove(){
			if( !rimovibile ) throw new IllegalStateException();
			rimovibile=false;
			for( int i=suc; i<size; i++ )
				contenuto[i-1]=contenuto[i];
			size--;
		}
	}

}
