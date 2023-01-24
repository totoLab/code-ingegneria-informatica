
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
	
	public boolean eAccesa() {
		return accesa;
	}
	
	public Lampadina (Lampadina l) { // copy constructor
		this.accesa = l.accesa;
	}
	
	public boolean equals(Object o) {
		// obvious cases first
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else if (!(o instanceof Lampadina)) {
			return false;
		}
		// in the end, verify that "this" object [status] is equal to "o" object [status]
		// in this case verify value of the only field: "accesa"
		Lampadina l = (Lampadina) o;
		return l.accesa == this.accesa;
	}
}
