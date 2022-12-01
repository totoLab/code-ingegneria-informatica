package poo.file;
import java.io.*;
import java.util.*;


/**
 * 
 * Cerca in un file di interi ordinato la presenza di un dato numero utilizzando la ricerca binaria.
 * Il file viene creato qualora nn dovesse esistere.
 *
 */
public class FileDemoRicercaBinaria{
	
	static void visualizza( String nome ) throws IOException {
        DataInputStream f=
        	new DataInputStream( new FileInputStream(nome) );
        int x=0;
        for(;;){
			try{
     			x=f.readInt();
		    }catch( EOFException e ){
				//EOF
				break;
			}
			System.out.println(x);
		}
	}//visualizza
	
	static boolean esiste( String nome, int x ) throws IOException{
		//dimostrazione dell'uso di r.a.f. con ricerca binaria
		RandomAccessFile f=new RandomAccessFile( nome, "r");
		int inf=0, sup=(int)(f.length()/4)-1;
		for(;;){
			if( inf>sup ) return false;
			int med=(inf+sup)/2;
			f.seek( med*4 );
			int elem=f.readInt();
			if( elem==x ) return true;
			if( elem>x ) sup=med-1;
			else inf=med+1;
		}
	}//esiste
	
	public static void main( String []args ) throws IOException {
		Scanner sc=new Scanner( System.in );
		System.out.print("Nome file=");
		String nomeFile=sc.next();
		File f=new File( nomeFile );
		if( !f.exists() ){
			//crea file
			DataOutputStream dos=new DataOutputStream(
				         new FileOutputStream( nomeFile ));
     		System.out.println("Fornire una sequenza ordinata di interi sino al primo non intero");
			int x=0;
			for(;;){
				try{
				    x=Integer.parseInt(sc.next());
				}catch( Exception e ){
					System.out.println("Fine sequenza"); break;
				}
				dos.writeInt( x );
			}
			dos.close();
		}
		System.out.println("File originario");
		visualizza( nomeFile );
		System.out.print("Valore da cercare x=");
		int x=sc.nextInt();
		if( esiste( nomeFile, x ) ) System.out.println(x+" esiste");
		else System.out.println(x+" non esiste");
		sc.close();
	}//main
}//DemoFile