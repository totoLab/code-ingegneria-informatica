package poo.contatori;

public class ContatoreModulare extends Contatore {
	
	protected int modulo; // 
	
	public ContatoreModulare() {
		super();
		this.modulo = 10;
	}
	
	public ContatoreModulare(int modulo) {
		super();
		if (modulo < 1) throw new IllegalArgumentException();
		this.modulo = modulo;
	}
	
	public ContatoreModulare(int valore, int modulo) {
		super(valore);
		if (modulo < 1 | valore < 0 || valore >= modulo) throw new IllegalArgumentException();
		this.modulo = modulo;
	}
	
	public ContatoreModulare(ContatoreModulare cm) {
		super(cm.valore);
		this.modulo = cm.modulo;
	}
	
	public int getModulo() {
		return modulo;
	}
	
	@Override
	public void incr() {
		valore = (valore + 1) % modulo;
	}
	
	@Override
	public void decr() {
		valore = (valore - 1 + modulo) % modulo;
	}

	@Override
	public String toString() {
		return "Contatore modulare: modulo" + modulo + ", valore " + valore;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof ContatoreModulare)) return false;
		
		ContatoreModulare cm = (ContatoreModulare) o;
		return this.valore == cm.valore &&
				this.modulo == cm.modulo;
	}
	
	public static void main(String[] args) {
		ContatoreModulare cm = new ContatoreModulare(10, 30);
		
		Class c = cm.getClass(); // reflection: capacità di ottenere metadata sulla classe

	}
}
