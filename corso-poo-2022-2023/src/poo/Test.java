package poo;

import poo.figure.*;

public class Test {


	public static void main(String[] args) {
		Figura f= new Cerchio(10);
		Cerchio c= new Cerchio(12);
		Figura f2 = new Rettangolo(4,6);
		
		Figura[] fa = {f,c,f2};
		//fa[0]=f;
		//fa[1]=c;
		//fa[2]=f2;
		
		Figura max = Figura.getAreaMassima(f,c,f2);
		
		System.out.println();
	
	}

}
