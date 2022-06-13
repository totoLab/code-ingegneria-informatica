package traccia03072020;

public class Acquisto {

	private String nomeNegozio;
	private int data;
	private String CFCliente;
	private String cittaCliente;
	
	public Acquisto(String nomeNegozio, int data, String cFCliente, String cittaCliente) {
		this.nomeNegozio = nomeNegozio;
		this.data = data;
		CFCliente = cFCliente;
		this.cittaCliente = cittaCliente;
	}

	public String getNomeNegozio() {
		return nomeNegozio;
	}

	public int getData() {
		return data;
	}

	public String getCFCliente() {
		return CFCliente;
	}

	public String getCittaCliente() {
		return cittaCliente;
	}
	
	public String toString() {
		return "Acquisto effettuato a " + nomeNegozio + " da " + CFCliente + " in data " + data;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Acquisto)) return false;
		
		Acquisto a = (Acquisto) o;
		return this.nomeNegozio.equals(a.nomeNegozio) &&
				this.CFCliente.equals(a.CFCliente) &&
				this.cittaCliente.equals(a.cittaCliente) &&
				this.data == a.data;
	}
}
