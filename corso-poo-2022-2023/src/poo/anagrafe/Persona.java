package poo.anagrafe;

import poo.util.Data;

public class Persona {
	
	public enum Sesso {Maschile, Femminile}
	
	public enum StatoCivile{Celibe, Nubile, Coniugato, Coniugata}
	
	private String nome;
	private String cognome;
	private Sesso s;
	private Data dataDiNascita;
	private String numTel;
	private Persona sposatoCon;
	
	private boolean maggiorenne;
	
	public Persona(String nome, String cognome, Data dataDiNascita, Sesso s) {
		this.nome=nome;
		this.cognome=cognome;
		this.dataDiNascita=dataDiNascita;
		this.s=s;
		this.maggiorenne=isMaggiorenne();
	}
	
	public Persona(Persona p) {
		if (p==null)
			throw new IllegalArgumentException();
		this.cognome=p.cognome;
		this.nome=p.nome;
		this.s=p.s;
		this.dataDiNascita=p.dataDiNascita;
		this.numTel=p.numTel;
		this.maggiorenne=p.maggiorenne;
		this.sposatoCon=p.sposatoCon;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Data getDataDiNascita() {
		return dataDiNascita;
	}
	
	public String getNumTel(){
		return numTel;
	}
	
	public void setNumTel(String n) {
		if (n==null)
			throw new IllegalArgumentException();
		this.numTel=n;
	}
	
	private boolean isMaggiorenne() {
		boolean maggiorenne;
		Data dataOdierna = new Data();
		if (dataOdierna.get(Data.Tipologia.Anno)-dataDiNascita.get(Data.Tipologia.Anno)>18)
			maggiorenne=true;
		else if (dataOdierna.get(Data.Tipologia.Anno)-dataDiNascita.get(Data.Tipologia.Anno)<18)
			maggiorenne=false;
		else if (dataOdierna.get(Data.Tipologia.Mese)>dataDiNascita.get(Data.Tipologia.Mese))
			maggiorenne=true;
		else if (dataOdierna.get(Data.Tipologia.Mese)<dataDiNascita.get(Data.Tipologia.Mese))
			maggiorenne=false;
		else maggiorenne=(dataOdierna.get(Data.Tipologia.Giorno)>=dataDiNascita.get(Data.Tipologia.Giorno));
		return maggiorenne;
	}
	
	
	public boolean maggiorenne() {
		if (maggiorenne==true)
			return maggiorenne;
		maggiorenne = isMaggiorenne();
		return maggiorenne;
	}
	
	
	
	public boolean sposaCon(Persona p) {
		if (p==null)
			throw new IllegalArgumentException();
		if (this==p)
			return false;
		if(!this.maggiorenne()||!p.maggiorenne())
			return false;
		if(this.sposatoCon!=null||p.sposatoCon!=null)
			return false;
		this.sposatoCon=p;
		p.sposatoCon=this;
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Cognome:").append(this.cognome).append('\n');
		sb.append("Nome:").append(this.nome).append('\n');
		sb.append(this.sposatoCon.cognome);
		
		return sb.toString();
		
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null)
				return false;
		} else if (!dataDiNascita.equals(other.dataDiNascita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (s != other.s)
			return false;
		return true;
	}

}
