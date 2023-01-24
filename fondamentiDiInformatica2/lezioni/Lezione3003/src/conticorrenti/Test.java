package conticorrenti;

import terminale.*;

public class Test 
{	public static void main(String[] args)
	{	ContoCorrente c1 = new ContoCorrente("ITQ0122345","Mario Rossi");
		c1.setFido(5000);
		c1.aggiungiMovimento(new Movimento("deposito",500));
		c1.aggiungiMovimento(new Movimento("deposito",100));
		c1.aggiungiMovimento(new Movimento("bonifico",-300));
		Terminale.stampa(c1);
			
		ContoCorrente c2 = new ContoCorrente("ITQ0129876","Luca Bianchi");
		c2.aggiungiMovimento(new Movimento("deposito",1000));
		c2.aggiungiMovimento(new Movimento("stipendio",1500));		
		Terminale.stampa(c2);
		
		ContoCorrente c3 = new ContoCorrente("ITQ088743","Giovanni Neri");
		c3.aggiungiMovimento(new Movimento("deposito",1000));
		c3.aggiungiMovimento(new Movimento("bonifico",-200));
		c3.aggiungiMovimento(new Movimento("stipendio",1700));		
		Terminale.stampa(c3);
		
		ContoCorrente c4 = new ContoCorrente("ITQ08172","Luca Rossi");
		c4.aggiungiMovimento(new Movimento("deposito",500));
		Terminale.stampa(c4);
		
		Banca banca = new Banca();
		banca.aggiungiContoCorrente(c1);
		banca.aggiungiContoCorrente(c2);
		banca.aggiungiContoCorrente(c3);
		banca.aggiungiContoCorrente(c4);
		
		Terminale.stampa(banca.clienteContoMax());
		Terminale.stampa(banca.contaDepositi());
		Terminale.stampa(banca.cercaContiSenzaMovimentiTipo("stipendio"));
	}	
}
