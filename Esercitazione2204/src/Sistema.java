import java.util.*;

public class Sistema {
	
	private LinkedList<Tecnico> tecnici;
	private LinkedList<Intervento> interventi;
	
	public Sistema(LinkedList<Tecnico> tecnici, LinkedList<Intervento> interventi) {
		this.tecnici = new LinkedList<>(tecnici);
		this.interventi = new LinkedList<>(interventi);
	}
	
	public Tecnico tecnicoMax() {
		// TODO
		return null;
	}
	public int costoComplessivo(Tecnico t) {
		int sommaDurateInterventi = 0;
		for (Intervento i : interventi) {
			if (i.getNomeTecnico().equals(t.getNome())) {
				sommaDurateInterventi += i.getDurata();
			}
		}
		return sommaDurataInterventi * t.getCostoOrario();
	}
}
