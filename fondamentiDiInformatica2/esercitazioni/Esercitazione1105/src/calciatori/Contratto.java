package calciatori;
import java.util.*;

public class Contratto {
	
	private String squadra;
	private String calciatore;
	private int prezzo;
	private LinkedList<Integer> premi;
	
	public String getSquadra() {
		return squadra;
	}
	
	public String getCalciatore() {
		return calciatore;
	}
	
	public int getPrezzo() {
		return prezzo;
	}
	
	public LinkedList<Integer> getPremi() {
		return premi;
	}
	
}
