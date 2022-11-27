package poo.agendina;
import java.util.*;
import java.io.*;

public class GestioneAgendinaVector{
	//ambiente globale - comune a tutti i metodi static
	static Agendina agenda;
	static String linea;
	static StringTokenizer st;
	static Scanner sc;

	public static void main( String []args ) throws IOException{
		System.out.println("Programma Agendina Telefonica");
		System.out.println();
		agenda=new AgendinaVector(); //oppure: new AgendinaAL() etc.
		sc=new Scanner( System.in );
		comandi();
		ciclo: for(;;){
			System.out.print(">");
			linea=sc.nextLine();
			st=new StringTokenizer(linea, " ");
			char comando=(st.nextToken().toUpperCase()).charAt(0);
			switch( comando ){
				case 'Q': quit(); break ciclo;
				case 'A': aggiungiNominativo(); break;
				case 'R': rimuoviNominativo(); break;
				case 'T': ricercaTelefono(); break;
				case 'P': ricercaPersona(); break;
				case 'Z': azzera(); break;
				case 'E': mostraElenco(); break;
				case 'S': salva(); break;
				case 'C': carica(); break;
				default: errore();
			}
		}//for
		System.out.println("Bye.");
		sc.close();
	}//main

	static void aggiungiNominativo(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			String pre=st.nextToken();
			String tel=st.nextToken();
			Nominativo n=new Nominativo( cog, nom, pre, tel );
			agenda.aggiungi( n );
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//aggiugiNominativo

	static void rimuoviNominativo(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			agenda.rimuovi( new Nominativo(cog, nom, "", "") );
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//rimuoviNominativo

	static void azzera(){
		agenda.svuota();
	}//azzera

	static void ricercaTelefono(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			Nominativo n=agenda.cerca( new Nominativo(cog, nom, "", "") );
			if( n==null )
				System.out.println("Nominativo inesistente!");
			else
				System.out.println(n.getPrefisso()+"-"+n.getNumero());
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//ricercaTelefono

	static void ricercaPersona(){
		try{
			String pre=st.nextToken();
			String tel=st.nextToken();
			Nominativo n=agenda.cerca( pre, tel );
			if( n==null )
				System.out.println("Nominativo inesistente!");
			else
				System.out.println(n.getCognome()+" "+n.getNome());
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//ricercaPersona

	static void mostraElenco(){
		System.out.println( agenda );
	}//mostraElenco

	static void salva(){
		String nomeFile=null;
		try{
        		nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		try{
			agenda.salva( nomeFile );
		}catch( IOException e ){
			e.printStackTrace();
			System.out.println("Nessun salvataggio!");
		}
	}//salva

	static void carica(){
		String nomeFile=null;
		try{
        	nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		File f=new File( nomeFile );
		if( !f.exists() ){
			System.out.println("File inesistente!");
			return;
		}
		try{
			agenda.ripristina( nomeFile );
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Nessuna apertura!");
		}
	}//carica

	static void comandi(){
		System.out.println();
		System.out.println("Comandi ammessi e relativi parametri:");
		System.out.println("A(ggiungi  cog  nom  pre  tel");
		System.out.println("R(imuovi  cog  nom");
		System.out.println("Z(azzera");
		System.out.println("T(elefono  cog  nom");
		System.out.println("P(persona  pre  tel");
		System.out.println("E(lenco");
		System.out.println("S(alva  nomefile");
		System.out.println("C(arica  nomefile");
		System.out.println("Q(uit");
		System.out.println();
	}//comandi

	static void errore(){
		System.out.println("Comando sconosciuto!");
		comandi();
	}//errore

	static void quit(){
		System.out.print("Vuoi salvare il contenuto dell'agenda prima di terminare(y/n)?");
		String yesno=sc.nextLine();
		if( yesno.toLowerCase().equals("y") ){
			System.out.print("nome file=");
			String nomeFile=sc.nextLine();
			try{
				agenda.salva( nomeFile );
			}catch( IOException ioe ){
				System.out.println("Errore salvataggio!");
			}
		}
	}//quit

}//GestioneAgendina
