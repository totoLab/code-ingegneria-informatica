package traccia20072021;

public class Calciatore {

	private String nome;
	private String squadraAttuale;
	
	public Calciatore(String nome, String squadraAttuale) {
		this.nome = nome;
		this.squadraAttuale = squadraAttuale;
	}

	public String getNome() {
		return nome;
	}

	public String getSquadraAttuale() {
		return squadraAttuale;
	}

	public String toString() {
		return "Calciatore " + nome + " della squadra " + squadraAttuale;
	}

	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Calciatore)) return false;
		
		Calciatore c = (Calciatore) o;
		return this.nome.equals(c.nome) && this.squadraAttuale.equals(c.squadraAttuale);
	}
}
