package traccia20092021;

public class Incontro {

	private int numeroEdizione;
	private String vincitore;
	private String vinto;
	private String fase;
	
	public Incontro(int numeroEdizione, String vincitore, String vinto, String fase) {
		this.numeroEdizione = numeroEdizione;
		this.vincitore = vincitore;
		this.vinto = vinto;
		this.fase = fase;
	}

	public int getNumeroEdizione() {
		return numeroEdizione;
	}

	public String getVincitore() {
		return vincitore;
	}

	public String getVinto() {
		return vinto;
	}

	public String getFase() {
		return fase;
	}
	
	public String toString() {
		return "Incontro disputato nell'edizione " + numeroEdizione +
				" vinto da " + vincitore + " contro " + vinto +
				" durante la " + fase;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Incontro)) return false;
		
		Incontro i = (Incontro) o;
		return this.numeroEdizione == i.numeroEdizione &&
				this.vincitore.equals(i.vincitore) &&
				this.vinto.equals(i.vinto) &&
				this.fase.equals(i.fase);
	}
}
