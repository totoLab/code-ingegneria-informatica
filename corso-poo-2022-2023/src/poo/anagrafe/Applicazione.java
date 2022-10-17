package poo.anagrafe;
import java.util.Scanner;
public class Applicazione {

	static Anagrafe ufficio = new Anagrafe(100);
	static Scanner sc = new Scanner(System.in);
	
	static enum Op {AGGIUNGI, CANCELLA, RICERCA, STAMPA, ESCI};
	
	public static void main(String[] args) {
		System.out.println("Menù di scelta");
		// inserire ciclo
		Op scelta = Op.ESCI;
		switch (scelta) {
		case AGGIUNGI:
			aggiungi();
			break;
		case CANCELLA:
			cancella();
			break;
		case RICERCA:
			ricerca();
			break;
		case STAMPA:
			stampa();
			break;
		case ESCI:
			esci();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + scelta);
		}
	}
	
	private static void aggiungi() {
		// TODO Auto-generated method stub

	}
	
	private static void cancella() {
		// TODO Auto-generated method stub

	}
	
	private static void ricerca() {
		// TODO Auto-generated method stub

	}
	
	private static void stampa() {
		// TODO Auto-generated method stub

	}
	
	private static void esci() {
		// TODO Auto-generated method stub

	}
	
}
