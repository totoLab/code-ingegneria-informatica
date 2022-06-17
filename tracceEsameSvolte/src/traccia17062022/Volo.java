package traccia17062022;

public class Volo {
	
	private String partenza;
	private String arrivo;
	private int prezzoBusiness;
	private int prezzoEconomica;
	
	public Volo(String partenza, String arrivo, int prezzoBusiness, int prezzoEconomica) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.prezzoBusiness = prezzoBusiness;
		this.prezzoEconomica = prezzoEconomica;
	}

	public String getPartenza() {
		return partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

	public int getPrezzoBusiness() {
		return prezzoBusiness;
	}

	public int getPrezzoEconomica() {
		return prezzoEconomica;
	}
	
	public String toString() {
		return "Volo da " + partenza + " a " + arrivo +
				" (B: €" + prezzoBusiness + ", E: €" + prezzoEconomica + ")";
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Volo)) return false;
		
		Volo v = (Volo) o;
		return this.partenza.equals(v.partenza) &&
				this.arrivo.equals(v.arrivo) &&
				this.prezzoBusiness == v.prezzoBusiness &&
				this.prezzoEconomica == v.prezzoEconomica;
	}
}
