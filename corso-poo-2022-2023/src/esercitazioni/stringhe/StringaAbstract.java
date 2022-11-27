package esercitazioni.stringhe;
import java.util.Iterator;

public abstract class StringaAbstract implements StringaIF {

	//approccio conservativo/prototipale
	public int length(){
		int l=0;
		for( char c: this ) l++;
		return l;
	}

	public void append( StringaIF s ){
		for( char c: s ) this.append( c );
	}

	public void append( String s ){
		for( int i=0; i<s.length(); i++ )
			this.append( s.charAt(i) );
	}
	
	public abstract void append( char c );

	public int indexOf( char x ){
		int i=0;
		for( char c: this ){
			if( c==x ) return i;
			i++;
		}
		return -1;
	}
	
	public int indexOf( char x , int da ){
		int i=0;
		for( char c: this ){
			if( i>=da && c==x ) return i;
			i++;
		}
		return -1;
	}
	
	public int indexOf( StringaIF s ){
		String dest=s.toString();
		String source=this.toString();
		return source.indexOf( dest );
	}
	
	public int indexOf( StringaIF s, int da ){
		String dest=s.toString();
		String source=this.toString();
		return source.indexOf( dest, da );
	}
	
	public abstract StringaIF copy();

	public StringaIF substring( int da ){
		String s=this.toString();
		StringaIF sub=newInstanceStringa();
		sub.append( s.substring(da) );
		return sub;
	}
	
	public StringaIF substring( int da, int a ){
		String s=this.toString();
		StringaIF sub=newInstanceStringa();
		sub.append( s.substring(da,a) );
		return sub;
	}
	
	public char charAt( int pos ){
		if( pos<0 || pos>=this.length() )
			throw new IndexOutOfBoundsException();
		int i=0;
		for( char c: this ){
			if( i==pos ) return c;
			i++;
		}
		return 0; //return fittizia
	}
	
	public abstract void setCharAt( int pos, char c );
	public char remove( int i ){
		if( i<0 || i>=this.length() )
			throw new IndexOutOfBoundsException();
		Iterator<Character> it=this.iterator();
		char c=0;
		for( int j=0; j<=i; j++ ) c=it.next();
		it.remove(); return c;
	}

	public void remove( int da, int a ){
		if( da<0 || da>=this.length() ||
		     a<0 || a>=this.length() || da>a )
			throw new IllegalArgumentException();
		for( int i=a-1; i>=da; i-- ) this.remove(i);
	}
	
	public void lowerCase(){
		for( int i=0; i<this.length(); i++ ){
			char c=this.charAt(i);
			if( c>='A' && c<='Z' ){
				c=(char)((c-'A')+'a');
				this.setCharAt(i,c);
			}
		}
	}
	
	public void upperCase(){
		for( int i=0; i<this.length(); i++ ){
			char c=this.charAt(i);
			if( c>='a' && c<='z' ){
				c=(char)((c-'a')+'A');
				this.setCharAt(i,c);
			}
		}
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		for( int i=0; i<this.length(); i++ )
			sb.append( this.charAt(i) );
		return sb.toString();
	}
	
	public int hashCode(){
		return this.toString().hashCode();
	}
	
	public boolean equals( Object x ){
		if( !(x instanceof StringaIF) ) return false;
		StringaIF s=(StringaIF)x;
		return this.toString().equals( s.toString() );
	}
	
	public int compareTo( StringaIF x ){
		return this.toString().compareTo( x.toString() );
	}
	
	public abstract Iterator<Character> iterator();
	protected abstract StringaIF newInstanceStringa();
}