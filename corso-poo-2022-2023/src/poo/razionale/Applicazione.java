package poo.razionale;

import java.util.Scanner;

public class Applicazione {

	public static void main(String[] args) {
		
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

}
