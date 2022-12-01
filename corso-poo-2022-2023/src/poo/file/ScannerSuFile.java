package poo.file;
import java.io.*;
import java.util.*;

/**
 *
 * Scanner "attaccato" ad un file testo anziche' alla
 * tastiera.
 *
 */
public class ScannerSuFile{
	public static void main( String... args ) throws IOException{
		Scanner sc=new Scanner( new File("c:\\poo-file\\dati.txt") );
		int x=sc.nextInt();
		double y=sc.nextDouble();
		System.out.println("x="+x+" y="+y);
	}
}