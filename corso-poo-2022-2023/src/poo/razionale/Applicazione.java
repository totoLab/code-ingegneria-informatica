package poo.razionale;

import java.util.Scanner;

public class Applicazione {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Razionale[] v = new Razionale[10];
		
		int i = 0, n = 0, d = 0;
		int fallimenti = 0;
		while (i < v.length) {
			System.out.println("numeratore: "); n = sc.nextInt();
			boolean corretto = false;
			while (corretto) {
				System.out.println("denominatore: "); d = sc.nextInt();
				
				if (d == 0) {
					System.out.println("Inserire nuovamente valore");
				} else {
					break;
				}
			}
			v[i] = new Razionale(n, d);
			i++;
		}
	}
	
	public static void esempioGestioneSingolaEccezione(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Razionale[] v = new Razionale[10];
		
		int i = 0, n = 0, d = 0;
		int fallimenti = 0;
		while (i < v.length) {
			System.out.println("numeratore: "); n = sc.nextInt();
			System.out.println("denominatore: "); d = sc.nextInt();
			
			try {
				v[i] = new Razionale(n, d);
				i++;
				System.out.println("Razionale costruito correttamente.");
			} catch (DenominatoreNulloException e) {
				e.printStackTrace();
				fallimenti++;
				if (fallimenti >= 3) {
					System.out.println("Hai finito i tentativi");
					break;
				} else {
					System.out.println("Denominatore nullo, reinserire.");
				}
			}
		}
		
	}
	
	public static void main1(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Razionale r1 = new Razionale(10,30);
		
		System.out.println(r1);
		
		System.out.println("Inserisci numeratore:");
		int num = sc.nextInt();
		System.out.println("Inserisci denominatore:");
		int den = sc.nextInt();
		
		Razionale r2= new Razionale(num,den);
		
		Razionale somma = r1.add(r2).mul(-1);
		
		System.out.println(somma);
		
		System.out.println( Razionale.getContatore() );
		
	}
	
	public static void esempioTryCatch(String[] args) {
		
		Razionale r1;
		try {
			
			r1 = new Razionale(10, 2);
			
		} catch (DenominatoreNulloException e) {
			e.printStackTrace();
		}
		
		Razionale r2 = new Razionale(7, 81);
		System.out.println(r2);
	}

}
