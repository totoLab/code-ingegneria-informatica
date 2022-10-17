package poo.anagrafe;
import poo.util.Data;
import poo.util.Data.Tipologia;

public class Persona {

	public enum Sesso {Maschile, Femminile}
	public enum StatoCivile {Celibe, Nubile, Coniugato, Coniugata}
	
	private String nome;
	private String cognome;
	private Sesso s;
	private Data dataDiNascita;
	private String numTel;
	private Persona sposatoCon;
	
	private boolean maggiorenne;
	
	public Persona(String nome, String cognome, Data dataDiNascita, Sesso s) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.s = s;
		this.maggiorenne = isMaggiorenne();
	}
	
	public Persona(Persona p) {
		if (p == null) throw new IllegalArgumentException();
		this.nome = p.nome;
		this.cognome = p.cognome;
		this.dataDiNascita = p.dataDiNascita;
		this.s = p.s;
		this.numTel = p.numTel;
		this.sposatoCon = p.sposatoCon; // aliasing ok 
		this.maggiorenne = p.maggiorenne;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Sesso getS() {
		return s;
	}

	public Data getDataDiNascita() {
		return dataDiNascita;
	}

	public String getNumTel() {
		return numTel;
	}
	
	public void setNumTel(String n) {
		if (n == null) throw new IllegalArgumentException();
		this.numTel = n;
	}

	public Persona getSposatoCon() {
		return sposatoCon;
	}
	
	private boolean isMaggiorenne() {
		Data dataOdierna = new Data();
		int condizioneAnno = dataOdierna.get(Tipologia.ANNO) - dataDiNascita.get(Tipologia.ANNO);
		int condizioneMese = dataOdierna.get(Tipologia.MESE) - dataDiNascita.get(Tipologia.MESE);
		int condizioneGiorno = dataOdierna.get(Tipologia.GIORNO) - dataDiNascita.get(Tipologia.GIORNO);
		
		if (condizioneAnno > 18) {
			return true;
		} else if (condizioneAnno < 18) {
			return false;
		} else {
			
			if (condizioneMese > 0) {
				return true;
			} else if (condizioneMese < 0) {
				return false;
			} else {
				
				if (condizioneGiorno >= 0) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public boolean maggiorenne() {
		if (maggiorenne) return true;
		
		this.maggiorenne = isMaggiorenne();
		return maggiorenne;
	}
	
	public StatoCivile getStatoCivile() {
		
		return null;
	}

	public boolean sposaCon(Persona p) {
		if (p == null) throw new IllegalArgumentException();
		if (this == p) return false;
		if (!this.maggiorenne() || !p.maggiorenne()) return false;
		if (this.sposatoCon != null || p.sposatoCon != null) return false;
		
		this.sposatoCon = p;
		p.sposatoCon = this;
		return true;
	}
	
	public String toStringRidotto() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cognome: ").append(this.cognome).append("\n");
		sb.append("Nome: ").append(this.nome).append("\n");
		
		return sb.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cognome: ").append(this.cognome).append("\n");
		sb.append("Nome: ").append(this.nome).append("\n");
		sb.append("Sesso: ").append(this.s).append("\n");
		sb.append("Data di nascita: ").append(this.dataDiNascita).append("\n");
		sb.append("Maggiorenne: ").append(this.maggiorenne).append("\n");
		sb.append("Numero di telefono: ").append(this.numTel).append("\n");
		sb.append("Cognome: ").append(this.sposatoCon.toStringRidotto()).append("\n");
			
		return sb.toString();
	}
}
