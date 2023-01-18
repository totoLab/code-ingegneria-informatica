package tracceesame.appello18012023;

public class Coppia {
	
	int scaffale;
	int slot;
	
	public Coppia(int scaffale, int slot) {
		this.scaffale = scaffale;
		this.slot = slot;
	}

	@Override
	public int hashCode() {
		int h = 0;
		int prime = 31;
		h = h + prime * scaffale;
		h = h + prime * slot;
		return h;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Coppia)) return false;
		
		Coppia other = (Coppia) obj;
		return scaffale == other.scaffale && slot == other.slot;
	}
	
	@Override
	public String toString() {
		return "Coppia [" +  scaffale + ", " + slot +"]";
	}
	
}
