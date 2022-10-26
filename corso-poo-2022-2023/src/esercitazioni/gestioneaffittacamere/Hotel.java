package esercitazioni.gestioneaffittacamere;

public class Hotel {
	private Stanza[] stanze;
	private String nome;
	private int numStelle;
	private String via;
	private String citta;

	public Hotel(Stanza[] stanze, String nome, int numStelle, String via, String citta) {
		this.stanze = stanze;
		this.nome = nome;
		this.numStelle = numStelle;
		this.via = via;
		this.citta = citta;
	}

	public boolean prenota(int index) {
		if (index < stanze.length) {
			if (!this.stanze[index].getLibera()) {
				this.stanze[index].effettuaPrenotazione();
				return true;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return false;
	}
	
	public void aggiungiStanza(Stanza s) {
		// TODO
	}
	
	public static void main(String[] args) {
		int numStanze = 90;
		Stanza[] stanze = new Stanza[numStanze];
		for (int i = 0; i < stanze.length; i++) {
			stanze[i] = new Stanza(4, 90.0, ""+i);
		}
		
		String nome = "Tazza"; int numStelle = 5; String via = "Argine Destro Calopinace, 69"; String citta = "RC";
		Hotel h = new Hotel(stanze, nome, numStelle, via, citta);
		stanze = new Stanza[numStanze*2];
		h.aggiungiStanza(new Stanza(5, 80.0, "NEW"));
		
	}
}
