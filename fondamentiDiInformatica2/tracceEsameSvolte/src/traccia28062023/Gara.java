package traccia28062023;

import java.util.*;

public class Gara {
	
	String ID, luogo;
	LinkedList<String> piloti, scuderie;
	
	public Gara(String ID, String luogo, LinkedList<String> piloti, LinkedList<String> scuderie) {
		super();
		this.ID = ID;
		this.luogo = luogo;
		this.piloti.addAll(piloti);
		this.scuderie.addAll(scuderie);
	}

	public String getID() {
		return ID;
	}
	
	public String getLuogo() {
		return luogo;
	}
	
	public LinkedList<String> getPiloti() {
		return new LinkedList<>(piloti);
	}
	public LinkedList<String> getScuderie() {
		return new LinkedList<>(scuderie);
	}
	
	
}
