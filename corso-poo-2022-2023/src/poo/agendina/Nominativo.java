package poo.agendina;

public final class Nominativo implements Comparable<Nominativo> {

	private String cognome, nome;
	private String prefisso, numero;
	
	public Nominativo(String nome, String cognome, String prefisso, String numero) {
		this.cognome = cognome;
		this.nome = nome;
		this.prefisso = prefisso;
		this.numero = numero;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getPrefisso() {
		return prefisso;
	}

	public String getNumero() {
		return numero;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nome + " ");
		sb.append(cognome + " ");
		sb.append("+" + prefisso + "-");
		sb.append(numero);
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nominativo other = (Nominativo) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public int compareTo(Nominativo o) {
		if (this.cognome.compareTo(cognome) < 0 ||
				this.cognome.equals(o.cognome) &&
				this.nome.compareTo(o.nome) < 0) return -1;
		if (this.equals(o)) return 0;
		return 1;
	}
	
}
