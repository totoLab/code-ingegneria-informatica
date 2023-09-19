package traccia28062023;

public class Scuderia {

	String nome, citta;
	float compensoExtra;
	
	public Scuderia(String nome, String citta, float compensoExtra) {
		super();
		this.nome = nome;
		this.citta = citta;
		this.compensoExtra = compensoExtra;
	}

	public String getNome() {
		return nome;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public float getCompensoExtra() {
		return compensoExtra;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
