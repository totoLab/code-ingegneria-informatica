package poo.file;
//copia di file

import java.io.*;

/**
 * 
 * Copia il contenuto di un file SOURCE su un file DEST
 *
 */
public class Copia{
	public static void main( String []args ) throws IOException {
		final String SOURCE="c:\\poo-file\\f1.dat";
		final String DEST="c:\\poo-file\\f2.dat";
		FileInputStream source=new FileInputStream(SOURCE);
		FileOutputStream dest=new FileOutputStream(DEST);
		int dato;
		for(;;){
			if( source.available()==0 ) break;
			dato=source.read();
			//if( dato==-1 ) break;
			dest.write( dato );
		}
		source.close();
		dest.close();
	}//main
}//Copia