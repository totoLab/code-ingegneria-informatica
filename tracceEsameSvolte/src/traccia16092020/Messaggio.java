package traccia16092020;

public class Messaggio {
	
	private String nomeMittente;
	private String nomeDestinatario;
	private int data;
	private boolean letto;
	
	public Messaggio(String nomeMittente, String nomeDestinatario, int data, boolean letto) {
		this.nomeMittente = nomeMittente;
		this.nomeDestinatario = nomeDestinatario;
		this.data = data;
		this.letto = letto;
	}

	public String getNomeMittente() {
		return nomeMittente;
	}
	
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	
	public int getData() {
		return data;
	}
	
	public boolean letto() {
		return letto;
	}
	
	public String toString() {
		String ret = "Messaggio inviato da " + nomeMittente 
				+ " a " + nomeDestinatario + " in data " + data;
		if (!letto) { ret += " non"; }
		return ret += " è stato letto";
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Messaggio)) return false;
		
		Messaggio m = (Messaggio) o;
		return this.nomeMittente.equals(m.nomeMittente) &&
				this.nomeDestinatario.equals(m.nomeDestinatario) &&
				this.data == m.data &&
				this.letto == m.letto;
	}	
}
