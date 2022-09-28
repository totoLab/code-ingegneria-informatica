package rubrica;

public class NumeroTelefonico { 		//LA CLASSE CONTIENE OGGETTI IMMUTABILI
	private String tipo; //casa, cellulare, ...
	private String numero;
	
	public NumeroTelefonico(String tipo, String numero) {
		this.tipo = tipo;
		this.numero = numero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String toString() {
		return tipo+" "+numero;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof NumeroTelefonico))
			return false;
		NumeroTelefonico n = (NumeroTelefonico) obj;
		return tipo.equals(n.tipo) && numero.equals(n.numero);
	}
}
