package poo.util;

import java.util.Scanner;

import poo.util.Data.Tipologia;

public class Test{
public static void main( String[] args ){
Scanner sc=new Scanner(System.
in);
System.
out.println("Fornisci cognome e nome di una persona ");
String linea=sc.nextLine();
linea=linea.trim(); //elimina spazi iniziali e finali
int i=linea.indexOf(' ');
String cognome=linea.substring(0,i);
//salta spazi
while( i<=linea.length() && linea.charAt(i)==' ' ) i++;
String nome=linea.substring(i);
System.
out.println(nome.charAt(0)+". "+cognome);
}//main
}//TestString
