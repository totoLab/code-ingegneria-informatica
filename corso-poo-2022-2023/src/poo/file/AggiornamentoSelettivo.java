package poo.file;

import java.io.*;
import java.util.*;
/**
 * Dato un file tipato di interi, supposto ordinato per
 * valori crescenti, ed un numero x, si desidera aggiungere
 * x al file preservando l'ordine (insertion sort su file).
 * A questo scopo si usa un file temporaneo su cui si
 * copiano tutti i valori del file originario minori o uguali ad x.
 * Quindi si copia x e, infine, tutti i restanti valori
 * del file di partenza. Dopo questo si cancella il file di
 * partenza (con i servizi degli oggetti File) e si ridenomina
 * il file temporaneo con lo stesso nome del file di origine.
 * @author Libero Nigro
 */
public class AggiornamentoSelettivo{
	public static void main( String []args )throws IOException {
		Scanner sc=new Scanner( System.in );
		System.out.print("nome file=");
		String nome=sc.next();
		System.out.print("intero da aggiungere ");
		int x=sc.nextInt();
		inserisci( nome, x );
		sc.close();
	}//main
	static void mostra( String nome ) throws IOException{
		System.out.println("Contenuto del file "+nome);
		InputStream in=new BufferedInputStream( new FileInputStream(nome) );
		DataInputStream dis=new DataInputStream( in );
		for(;;){
			try{
			int x=dis.readInt();
			System.out.println(x);
			}catch(Exception e ){ break; }
		}
		dis.close();
	}//mostra
	static void inserisci( String nome, int x )throws IOException{
		mostra( nome );
		RandomAccessFile raf=new RandomAccessFile( nome, "r" );
		DataOutputStream tmp=
		   new DataOutputStream( new FileOutputStream("tmp") );
		long pos=0;
		int y=0;
		boolean flag=false;
		while( pos<raf.length() && !flag ){
			y=raf.readInt();
			if( y>x ) flag=true;
			else tmp.writeInt( y );
			pos=raf.getFilePointer();
		}//while
		tmp.writeInt( x );
		if( flag ){
		  for(;;){
			tmp.writeInt( y );
			pos=raf.getFilePointer();
			if( pos==raf.length() ) break;
			y=raf.readInt();
	      }
	    }
		tmp.close(); raf.close();
		mostra("tmp");
		//gestione file
		File f=new File(nome); f.delete();
		File ff=new File("tmp");
		System.out.println(ff.renameTo(f));
	}
}//AggiornamentoSelettivo