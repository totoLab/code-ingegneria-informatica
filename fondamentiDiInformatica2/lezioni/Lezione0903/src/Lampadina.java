
public class Lampadina {
	private boolean accesa;
	
	public Lampadina () {
		accesa = false;
	}
	
	public String toString() {
		if (accesa) {
			return "Lampadina accesa";
		} else {
			return "Lampadina spenta";
		}
	}
	
	public void premiBottone() {
		accesa = !(accesa);
	}
}
