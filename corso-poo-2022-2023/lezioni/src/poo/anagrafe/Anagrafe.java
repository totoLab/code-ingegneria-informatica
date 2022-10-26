package poo.anagrafe;

public class Anagrafe {
	
	private Persona[] elenco;
	private int numeroPersone;
	
	private final boolean dimDinamica;
	
	
	public Anagrafe(int dim, boolean dimDinamica) {
		if (dim<=0)
			throw new IllegalArgumentException();
		elenco = new Persona[dim];
		numeroPersone=0;
		this.dimDinamica=dimDinamica;
	}
	
	public Anagrafe(int dim) {
		this(dim,false);
	}
	
	public Anagrafe() {
		this(100);
	}
	
	public Anagrafe(Persona[] elenco, int dim, boolean dimDinamica) {
		if (dim<elenco.length)
			throw new IllegalArgumentException();
		this.elenco=new Persona[dim];
		for(int i=0; i<elenco.length; i++)
			this.elenco[i]=elenco[i];
		numeroPersone=elenco.length;
		this.dimDinamica=dimDinamica;
	}
	
	public Anagrafe(Persona[] elenco, int dim) {
		this(elenco,dim,false);
	}
	
	public Anagrafe(Anagrafe a) {
		this(a.elenco,a.elenco.length,a.dimDinamica);
	}
	
	public boolean aggiungi(Persona p) {
		if (dimDinamica==false && numeroPersone>=elenco.length)
			return false;
		if (dimDinamica==true && numeroPersone>=elenco.length)
			resize();
		for(int i=0; i<numeroPersone; i++) {
			if (p.getNome().equalsIgnoreCase(elenco[i].getNome()) && 
					p.getCognome().equalsIgnoreCase(elenco[i].getCognome()))
					return false;
		}
		elenco[numeroPersone]=p;
		numeroPersone++;
		return true;
	}
	
	public int getNumeroPersone() {
		return numeroPersone;
	}
	
	public boolean anagrafePiena() {
		if (dimDinamica==true)
			return false;
		return numeroPersone==elenco.length;
	}
	
	private void resize() {
		Persona[] nuovoElenco = new Persona[elenco.length*2];
		System.arraycopy(elenco, 0, nuovoElenco, 0, numeroPersone);
		elenco=nuovoElenco;
	}
	
	public boolean trova(Persona p) {
		for(int i=0; i<numeroPersone; i++) {
			if (p.getNome().equalsIgnoreCase(elenco[i].getNome()) && 
					p.getCognome().equalsIgnoreCase(elenco[i].getCognome()))
					return true;
		}
		return false;
	}
	
	public boolean rimuovi(Persona p) {
		int index=-1;
		//ricerca
		for(int i=0; i<numeroPersone; i++) {
			if (p.getNome().equalsIgnoreCase(elenco[i].getNome()) && 
					p.getCognome().equalsIgnoreCase(elenco[i].getCognome())) {
					index=i;
					break;
			}
		}
		if (index==-1)
			return false;
		elenco[index]=elenco[numeroPersone-1];
		elenco[numeroPersone-1]=null;
		numeroPersone--;
		return true;
	}
	
	
	public Persona[] ricerca(String cognome) {
		int index = 0;
		for (int i = 0; i < numeroPersone; i++) {
		Persona p = elenco[i];
			if (p.getCognome().equals(cognome)) {
				index++;
			}
		}
		 		
		Persona[] ret = new Persona[index];
		index = 0;
		for (int i = 0; i < numeroPersone || index < ret.length; i++) {
			Persona p = elenco[i];
			if (p.getCognome().equals(cognome)) {
				ret[index] = elenco[i];
				index++;
			}
		}
		return ret;
	}

}
