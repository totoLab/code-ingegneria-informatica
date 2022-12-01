package poo.file;
import java.io.*;
import java.util.*;
/**
 * 
 * Crea un file di interi a partire da una successione
 * letta da tastiera, e terminante con una stringa vuota.
 *
 */
public class Crea{
	public static void main( String []args )throws IOException {
		Scanner s=new Scanner( System.in );
		System.out.print("nome file da creare=");
		String nome=s.nextLine();
		DataOutputStream dos=
			new DataOutputStream( new FileOutputStream(nome) );
		System.out.println("Fornisci una serie di interi uno per linea. Solo INVIO termina");
		for(;;){
			System.out.print("int>");
			String input=s.nextLine();
			if( input.length()==0 ) break;
			dos.writeInt( Integer.parseInt(input) );
		}
		dos.close();
		DataInputStream dis=
			new DataInputStream( new FileInputStream(nome) );
		System.out.println();
		System.out.println("Contenuto del file");
		int x=0;
		for(;;){
			try{
				x=dis.readInt();
			}catch( EOFException e ){ break; }
			System.out.println( x );
		}//for
		dis.close();
	}//main
}//Crea