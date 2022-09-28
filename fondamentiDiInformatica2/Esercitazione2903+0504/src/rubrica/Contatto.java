package rubrica;

public class Contatto { //GENERA OGGETTI MUTABILI
	private String nome, cognome, email;
	private int numeriMemorizzati;
	private NumeroTelefonico[] numeri;
	
	public Contatto(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		email = null; //email = "";
		numeriMemorizzati = 0;
		numeri = new NumeroTelefonico[5];
	}
	
	public Contatto(String nome, String cognome, NumeroTelefonico nt) {
		this.nome = nome;
		this.cognome = cognome;
		email = null;
		numeriMemorizzati = 1;
		numeri[0] = nt;
	}

	public Contatto(Contatto c) {
		nome = c.nome;
		cognome = c.cognome;
		email = c.email;
		numeriMemorizzati = c.numeriMemorizzati;
		numeri = new NumeroTelefonico[c.numeri.length]; 
		//protective copy poiché l'attributo è un tipo mutabile
		for(int i = 0; i < c.numeriMemorizzati; i++) 
			numeri[i] = c.numeri[i];
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		//return email;
		return email==null? "":email;
	}

	public NumeroTelefonico estraiNumero(String tipo) {
		NumeroTelefonico nt = null; 
		for(int i = 0; i < numeriMemorizzati; i++)
			if(numeri[i].getTipo().equals(tipo)) {
				nt = numeri[i];
				break;
			}
		return nt;
	}
	
	public void aggiungiNumero(NumeroTelefonico nt) {
		if(numeriMemorizzati < numeri.length)
			numeri[numeriMemorizzati] = nt;
		//Caso array saturo, numeriMemorizzati == numeri.length
		else {
			NumeroTelefonico[] nuovo = new NumeroTelefonico[numeriMemorizzati*2];
			for(int i = 0; i < numeriMemorizzati; i++)
				nuovo[i] = numeri[i];
			nuovo[numeriMemorizzati] = nt;
			numeri = nuovo;	
		}
		numeriMemorizzati++;
	}
	
	 public void eliminaNumero(NumeroTelefonico nt) { 
		  int indice = -1; 
		  for(int i=0; i<numeriMemorizzati;i++) 
		   if(numeri[i].equals(nt)) { 
		    indice = i; 
		    break; 
		   } 
		  if(indice!=-1) 
		   this.eliminaNumero(indice);//this.eliminaNumero(indice); 
	}
	 
	 public void eliminaNumero2(int indice) { 
		  for(int i=indice+1;i<numeriMemorizzati;i++) 
		   numeri[i-1] = numeri[i]; 
		  numeri[numeriMemorizzati-1] = null; 
		  numeriMemorizzati--; 
	} 
		  
	 public void eliminaNumero(int indice) { 
		  NumeroTelefonico[] nuovo = new NumeroTelefonico[numeri.length]; 
		  for(int i=0;i<indice;i++) 
		    nuovo[i] = numeri[i]; 
		  for (int i=indice+1; i<numeri.length; i++) 
			  nuovo[i-1] = numeri[i]; 
		  numeriMemorizzati--; 
		  numeri = nuovo; 
	 } 
	
	public String toString() {
		String ret = "======================\n"; //spazio tra le varie variabili
		ret += nome+ "  "+ cognome + "\n";
		if(email != null)
			ret += "Email: "+email+"\n";
		for(int i = 0; i < numeriMemorizzati; i++) 
			ret += numeri[i].toString() + "\n";
		ret += "=====================\n";
		return ret;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof Contatto))
			return false;
		Contatto c = (Contatto) obj;
		if(!nome.equals(c.nome) || !cognome.equals(c.cognome) || !email.equals(c.email) || numeriMemorizzati != c.numeriMemorizzati || !numeri.equals(c.numeri))
			return false;
		for(int i = 0; i < numeriMemorizzati; i++)
			if(!numeri[i].equals(c.numeri[i]))
				return false;
		return true;
		}
	


}
