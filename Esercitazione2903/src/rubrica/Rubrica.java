package rubrica; 
 
public class Rubrica { // mutabile
  
	private int contattiMemorizzati; 
	private Contatto[] contatti; 

	public Rubrica () {
		this.contattiMemorizzati = 0;
		this.contatti = new Contatto[5];
	}
	
	public Rubrica (Rubrica r) {
		this.contattiMemorizzati = r.contattiMemorizzati;
		this.contatti = new Contatto[r.contatti.length];
		for (int i = 0; i < this.contattiMemorizzati; i++) {
			contatti[i] = new Contatto(r.contatti[i]);
		}
	}
	
	public void aggiungiContatto(Contatto c) { 
		if(this.contattiMemorizzati < this.contatti.length) {
			contatti[contattiMemorizzati] = new Contatto(c);
		} else {
			Contatto[] nuovo = new Contatto[contattiMemorizzati*2];
			for (int i = 0; i < contattiMemorizzati; i++) {
				nuovo[i] = contatti[i];
			}
			nuovo[contattiMemorizzati] = new Contatto(c);
			contatti = nuovo;
		}
		contattiMemorizzati++;
	} 
  
	public void eliminaContatto(Contatto c) { 
		int indice = -1;
		for (int i = 0; i < contattiMemorizzati; i++) {
			if (contatti[i].equals(c)) {
				indice = 1;
				break;
			}
		}
		if (indice != -1) {
			Contatto[] nuovo = new Contatto[contatti.length];
			for (int i = 0; i < indice; i++) {
				nuovo[i] = contatti[i];
			}
			for (int i = indice + 1; i < contattiMemorizzati; i++) {
				nuovo[i - 1] = contatti[i];
			}
			this.contatti = nuovo;
			this.contattiMemorizzati--;
		}
	}
  
	public Contatto[] estraiContatti(String nome, String cognome) { 
		int numContatti = contaContatti(nome, cognome);
		Contatto[] ris = new Contatto[numContatti];
		int pos = 0;
		for (int i = 0; i < contattiMemorizzati; i++) {
			if (this.contatti[i].getNome().equals(nome) && this.contatti[i].getCognome().equals(cognome)) {
				ris[pos] = new Contatto(contatti[i]);
				pos++;
			}
		}
		
		return ris;
	} 
	
	private int contaContatti(String nome, String cognome) {
		int cont = 0;
		for (int i = 0; i < contattiMemorizzati; i++) {
			if (this.contatti[i].getNome().equals(nome) && this.contatti[i].getCognome().equals(cognome)) {
				cont++;
			}
		}
		
		return cont;
	}
	
	public String toString() {
		String ris = "==========Rubrica==========\n";
		for (int i = 0; i < contattiMemorizzati; i++) {
			ris += contatti[i].toString();
		}
		ris += "===========================\n";
		return ris;
	}
	
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rubrica)) {
			return false;
		}
		Rubrica r = (Rubrica) o;
		
		if (this.contattiMemorizzati != r.contattiMemorizzati) {
			return false;
		}
		for (int i = 0; i < this.contattiMemorizzati; i++) {
			if (!contatti[i].equals(r.contatti[i])) {
				return false;
			}
		}
		return true;
		
	}
}