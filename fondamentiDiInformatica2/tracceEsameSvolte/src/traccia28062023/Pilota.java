package traccia28062023;

public class Pilota {
	String nome;
	int eta;
	float compensoGara;
	
	public Pilota(String nome, int eta, float compensoGara) {
		super();
		this.nome = nome;
		this.eta = eta;
		this.compensoGara = compensoGara;
	}

	public String getNome() {
		return nome;
	}
	
	public int getEta() {
		return eta;
	}
	
	public float getCompensoGara() {
		return compensoGara;
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
