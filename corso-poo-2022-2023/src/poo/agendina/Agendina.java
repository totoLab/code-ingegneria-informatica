package poo.agendina;

import java.io.IOException;
import java.util.Iterator;

public interface Agendina extends Iterable<Nominativo> {

	default public int size() {
		Iterator<Nominativo> it = this.iterator();
		int cont = 0;
		while (it.hasNext()) {
			it.next();
			cont++;
		}
		return cont;
	}
	
	default public void svuota() {
		Iterator<Nominativo> it = this.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		} 
	}
	
	public void aggiungi(Nominativo n);
	
	default public void rimuovi(Nominativo n) {
		Iterator<Nominativo> it = this.iterator();
		while (it.hasNext()) {
			it.next();
			if (it.equals(n)) {
				it.remove();
			}
		}
	}
	
	default public Nominativo cerca(Nominativo n) {
		Iterator<Nominativo> it = this.iterator();
		while (it.hasNext()) {
			Nominativo ret = it.next();
			if (ret.equals(n)) {
				return ret;
			}
		}
		return null;
	}
	
	default public Nominativo cerca(String prefisso, String telefono) {
		Iterator<Nominativo> it = this.iterator();
		while (it.hasNext()) {
			Nominativo ret = it.next();
			if (ret.getPrefisso().equals(prefisso) &&
					ret.getNumero().equals(telefono)) {
				return ret;
			}
		}
		return null;
	}
	
	public void salva(String nomeFile) throws IOException;
	public void ripristina(String nomeFile) throws IOException;
	
}
