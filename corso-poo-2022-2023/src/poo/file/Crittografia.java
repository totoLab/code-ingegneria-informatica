package poo.file;
import java.io.*;

public class Crittografia{
	public static void main( String []args ) throws IOException {
		if( args.length==0 ){
			System.out.println("Attesa chiave!");
			System.exit(-1);
		}
		int chiave=Integer.parseInt( args[0] );
		InputStream source=new FileInputStream("c:\\poo-file\\testo2.txt");
		OutputStream dest=new FileOutputStream("c:\\poo-file\\testo1.txt");
		int dato;
		for(;;){
			dato=source.read();
			if( dato==-1 ) break;
			dest.write( crittografa( dato, chiave ) );
		}
		source.close();
		dest.close();
	}//main
	static byte crittografa( int d, int chiave ){
		return (byte)(d+chiave );
	}
}//Copia