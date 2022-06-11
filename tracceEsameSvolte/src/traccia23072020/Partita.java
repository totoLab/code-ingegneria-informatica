package traccia23072020;

public class Partita {

	private String nomeSquadraInCasa;
	private String nomeSquadraOspite;
	private int goalSquadraInCasa;
	private int goalSquadraOspite;
	private String nomeArbitro;
	private String cittaArbitro;
	
	public Partita(String nomeSquadraInCasa, String nomeSquadraOspite,
			int goalSquadraInCasa, int goalSquadraOspite,
			String nomeArbitro, String cittaArbitro) {
		this.nomeSquadraInCasa = nomeSquadraInCasa;
		this.nomeSquadraOspite = nomeSquadraOspite;
		this.goalSquadraInCasa = goalSquadraInCasa;
		this.goalSquadraOspite = goalSquadraOspite;
		this.nomeArbitro = nomeArbitro;
		this.cittaArbitro = cittaArbitro;
	}

	public String getNomeSquadraInCasa() {
		return nomeSquadraInCasa;
	}

	public String getNomeSquadraOspite() {
		return nomeSquadraOspite;
	}

	public int getGoalSquadraInCasa() {
		return goalSquadraInCasa;
	}

	public int getGoalSquadraOspite() {
		return goalSquadraOspite;
	}

	public String getNomeArbitro() {
		return nomeArbitro;
	}

	public String getCittaArbitro() {
		return cittaArbitro;
	}
	
	public String toString() {
		return "Partita tra" +
				nomeSquadraInCasa + "(" + goalSquadraInCasa + " goal)" +
				" e " + nomeSquadraOspite + "(" + goalSquadraOspite + " goal)" +
				", arbitrata da" + nomeArbitro + "(" + cittaArbitro + ").";	
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Partita)) return false;
		
		Partita p = (Partita) o;
		return this.nomeSquadraInCasa.equals(p.nomeSquadraInCasa) &&
				this.nomeSquadraOspite.equals(p.nomeSquadraOspite) &&
				this.goalSquadraInCasa == p.goalSquadraOspite &&
				this.nomeArbitro.equals(p.nomeArbitro) &&
				this.cittaArbitro.equals(p.cittaArbitro);
	}
	
}
