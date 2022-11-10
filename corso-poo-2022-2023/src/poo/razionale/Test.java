package poo.razionale;

import java.util.Scanner;

import poo.util.Array;

public class Test {

	public static void main(String[] args) {
		Razionale r1 = new Razionale(2, 1);
		Razionale r2 = new Razionale(3, 2);
		Razionale r;
		
		int res = r1.compareTo(r2);
		if (res > 0)
			r = r1;
		else if (res < 0)
			r = r2;
		else
			r = r1;
		System.out.println(r + "maggiore");
	}
		
	public static void fun() {
		/*Scanner sc = new Scanner(System.in);
		Razionale[] v = new Razionale[10];
		int i=0, n=0, d=0;
		int fallimenti=0;
		while(i<v.length) {
			System.out.println("numeratore:");n=sc.nextInt();
			System.out.println("denominatore:"); d=sc.nextInt();
			try {
				v[i]=new Razionale(n, d);
				//viene eseguito solo se eccezione non lanciata
				i++;
				System.out.println("Razionale Costruito Correttamente");
			}catch(DenominatoreNulloException e) {
				e.printStackTrace();
				fallimenti++;
				System.out.println("Errore denominatore nullo");
				if (fallimenti==3) {
					System.out.println("Hai finito i tentativi");
					break;
				}else {
					System.out.println("Inserire nuovamente il dato");
				}	
			}
			System.out.println("Ulteriore iterazione");
		}*/
		Scanner sc = new Scanner(System.in);
		Razionale[] v = new Razionale[10];
		int i=0, n=0, d=0;
		while(i<v.length) {
			System.out.println("numeratore:");n=sc.nextInt();
			while(true) {
				System.out.println("denominatore:"); d=sc.nextInt();
				if(d==0) {
				   System.out.println("Valore da inserire nuovamente");
				}else
					break;
			}
			v[i]=new Razionale(n, d);
			i++;
		}
	}

}
