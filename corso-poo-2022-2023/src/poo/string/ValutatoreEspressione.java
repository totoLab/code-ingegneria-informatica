package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ValutatoreEspressione {

	public static void main(String[] args) {
		String espr;
		if (args.length==1)
			espr=args[0];
		else {
			Scanner sc=new Scanner(System.in);
			System.out.print(">");
			espr=sc.nextLine();
		}
		
		StringTokenizer st = new StringTokenizer(espr, "+-*/", true);
		String val =st.nextToken();
		int ris=Integer.parseInt(val);
		while(st.hasMoreTokens()) {
			char op=st.nextToken().charAt(0);
			int num=Integer.parseInt(st.nextToken());
			switch(op) {
			case '+': ris=ris+num;break;
			case '-': ris=ris-num;break;
			case '*': ris=ris*num;break;
			default: ris=ris/num;
			}
		}
		System.out.println("ris="+ris);
	}

}
