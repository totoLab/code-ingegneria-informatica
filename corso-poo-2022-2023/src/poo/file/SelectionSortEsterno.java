package poo.file;
import java.util.*;
import java.io.*;
public class SelectionSortEsterno {
	static enum Direzione{ DA_F1_A_TEMP2, DA_TEMP2_A_F1 };
	
	/**
	 * 
	 * Aggiunge il contenuto di f1, partendo dall'attuale posizione della sua testina di lettura,
	 * in temp2 ad eccezione del minimo che viene aggiunto in temp1
	 * restituisce TRUE quando l'ObjectFile f1 passato come parametro non è arrivato a fine file  
	 * 
	 */
	static boolean cercaMin( DataInputStream f1, DataOutputStream temp1, DataOutputStream temp2 ) throws IOException{
		int min=0;
		try{
			min=f1.readInt();
		}catch( EOFException e ){
			return false;
		}
		for(;;){
			int x=0;
			try{
				x=f1.readInt();
			}catch( EOFException e ){
				break;
			}
			if( x<min ){ 
				temp2.writeInt(min); 
				min=x;
			}else 
				temp2.writeInt(x);
		}
		temp1.writeInt(min);
		return true;
	}//cercaMin
	public static void main( String[] args ) throws IOException{
		System.out.println("Ordinamento esterno di un file di interi con selection sort");
		Scanner sc=new Scanner( System.in );
		String nomeFile=null;
		boolean ok=false;
		do{
			System.out.print("Nome file : ");
			nomeFile=sc.nextLine();
			File f=new File(nomeFile);
			ok=f.exists();
			if( !ok ) 
				System.out.println(nomeFile+" non esiste. Ridare il nome del file.");
		}while( !ok );
		DataInputStream f1=new DataInputStream( new FileInputStream(nomeFile) );
		DataOutputStream ordinato=new DataOutputStream( new FileOutputStream("c:\\poo-file\\temp1"));
		DataOutputStream temp2=new DataOutputStream( new FileOutputStream("c:\\poo-file\\temp2"));
		Direzione d=Direzione.DA_F1_A_TEMP2;
		boolean continua=cercaMin( f1, ordinato, temp2 );
		while( continua ){
			switch( d ){
				case DA_F1_A_TEMP2:
					d=Direzione.DA_TEMP2_A_F1;
					f1.close(); temp2.close();
					f1=new DataInputStream( new FileInputStream("c:\\poo-file\\temp2"));
					temp2=new DataOutputStream( new FileOutputStream(nomeFile)); 
					break;
				default:
					d=Direzione.DA_F1_A_TEMP2;
					f1.close(); temp2.close();
					f1=new DataInputStream( new FileInputStream(nomeFile));
					temp2=new DataOutputStream( new FileOutputStream("c:\\poo-file\\temp2")); 
			}
			continua=cercaMin( f1, ordinato, temp2 );
		}//while
		f1.close(); ordinato.close(); temp2.close();
		
		//manutenzione file
		File w=new File( "c:\\poo-file\\temp2" );
		w.delete(); //temp2 e' rimosso
		//ridenomina temp1 come f1
		File y=new File( nomeFile );
		y.delete(); //f1 originario e' rimosso
		File z=new File("c:\\poo-file\\temp1");
		z.renameTo(y); //temp1 e' rinominato come nomeFile
		
		System.out.println("Contenuto file dopo ordinamento esterno");
		f1=new DataInputStream( new FileInputStream(nomeFile) );
		for(;;){
			int elem=0;
			try{
				elem=f1.readInt();
			}catch( EOFException ex ){ break; }
			System.out.println(elem);
		}
		f1.close();
	}//main
}//SelectionSortEsterno
