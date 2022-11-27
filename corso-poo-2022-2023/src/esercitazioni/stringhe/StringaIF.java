package esercitazioni.stringhe;

public interface StringaIF extends Comparable<StringaIF>,Iterable<Character> {
	
	int length();
	void append( StringaIF s );
	void append( String s );
	void append( char c );
	int indexOf( char x ); //cerca x a partire da 0
	int indexOf( char x , int da ); //cerca x a partire da "da"
	int indexOf( StringaIF s ); //cerca s a partire da 0
	int indexOf( StringaIF s, int da ); //usa la tua "fantasia"
	StringaIF copy();
	StringaIF substring( int da ); //da "da" sino alla fine
	StringaIF substring( int da, int a ); //da "da" ad "a" escluso
	char charAt( int pos );
	void setCharAt( int pos, char c ) ;
	char remove( int i );
	void remove( int da, int a ); //da "da" ad "a" escluso
	void lowerCase();
	void upperCase();
	String toString();
	int hashCode();
	boolean equals( Object x );

}