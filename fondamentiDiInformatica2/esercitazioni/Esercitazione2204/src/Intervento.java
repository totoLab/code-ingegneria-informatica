import java.util.*;

public class Intervento {
	
	private String nomeTecnico;
	private LinkedList<String> tipiOperazione = new LinkedList<>();
	private int data;
	private int durata;
	
	public Intervento(String nomeTecnico, LinkedList<String> tipiOperazione, int data, int durata) {
		this.nomeTecnico = nomeTecnico;
		this.data = data;
		this.durata = durata;
		for (String o : tipiOperazione) {
			this.tipiOperazione.add(o);
		}
	}
	
	public String getNomeTecnico() {
		return nomeTecnico;
	}
	
	public LinkedList<String> getTipiOperazione() {
		LinkedList<String> l = new LinkedList<>();
		for (String o : tipiOperazione) {
			l.add(o);
		}
		return l;
	}
	
	public int getData() {
		return data;
	}
	
	public int getDurata() {
		return durata;
	}
	
	public String toString() {
		String s = "Intervento effettuato dal tecnico " + nomeTecnico + ", in data " + data + ", durato per un totale di " + durata + "ore , che ha compreso operazioni quali:\n";
		for (String tipo : tipiOperazione) {
			s += "- " + tipo + "\n";
		}
		return s;
	}
	
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!(o instanceof Intervento))
			return false;
		
		Intervento i = (Intervento) o;		
		return nomeTecnico.equals(i.getNomeTecnico()) &&
				data == i.getData() &&
				durata == i.getDurata() &&
				tipiOperazione.equals(i.getTipiOperazione());
	}
}
