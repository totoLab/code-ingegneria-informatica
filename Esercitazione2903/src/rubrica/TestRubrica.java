package rubrica;

import terminale.*;

public class TestRubrica {

	public static void main(String[] args) {
		Contatto c1 = new Contatto("Mario", "Rossi");
		c1.setEmail("mario.rossi@gmail.com");
		c1.aggiungiNumero(new NumeroTelefonico("Ufficio", "0984495555"));
		c1.aggiungiNumero(new NumeroTelefonico("Casa", "0984635555"));
		
		Contatto c2 = new Contatto("Giuseppe", "Verdi");
		c2.setEmail("g.verdi@gmail.com");
		c2.aggiungiNumero(new NumeroTelefonico("Ufficio", "0984495556"));
		c2.aggiungiNumero(new NumeroTelefonico("Cellulare", "3333333333"));
		
		Terminale.stampa(c1);
		Terminale.stampa(c2);
		
		Rubrica rubrica = new Rubrica();
		rubrica.aggiungiContatto(c1);
		rubrica.aggiungiContatto(c2);
		Terminale.stampa(rubrica);
	}

}
