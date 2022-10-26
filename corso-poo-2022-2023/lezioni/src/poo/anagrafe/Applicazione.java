package poo.anagrafe;

import java.util.Scanner;

public class Applicazione {

	static Anagrafe ufficio = new Anagrafe(100);
	static Scanner sc = new Scanner(System.in);
	
	final static int AGGIUNGI=0;
	final static int CANCELLA=1;
	final static int RICERCA=2;
	final static int STAMPA=3;
	
	final static int ESCI=4;
	
	public static void main(String[] args) {
		boolean finito=false;
		while(!finito) {
			System.out.println("MENU' di scelta");
			System.out.println(AGGIUNGI+" -> Aggiungi Persona");
			System.out.println(CANCELLA+" -> Cancella Persona");
			System.out.println(RICERCA+" -> Ricerca Persona");
			System.out.println(STAMPA+" -> Stampa Anagrafe");
			System.out.println(ESCI+" -> Termina");
			System.out.print("Cosa vuoi fare?");
			int scelta=sc.nextInt();
			switch(scelta){
			case AGGIUNGI:aggiungi();break;
			case CANCELLA:cancella();break;
			case RICERCA:ricerca();break;
			case ESCI:finito=true;break;
			default: System.out.println("Errore nella scelta!");
			}
		}
	}

	public static void ricerca() {
		// TODO Auto-generated method stub
		
	}

	public static void cancella() {
		// TODO Auto-generated method stub
		
	}

	public static void aggiungi() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
