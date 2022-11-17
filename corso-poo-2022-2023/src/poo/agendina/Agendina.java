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
	
	default public void salva(String nomeFile) throws IOException {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(nomeFile));
			Iterator<Nominativo> it = this.iterator();
			while (it.hasNext()) {
				pw.println(it.next());
			}
		} finally {
			if (pw != null) pw.close();
		}
	}
	
	default public void ripristina(String nomeFile) throws IOException {
		svuota();
		BufferedReader br = null; 
		Vector<Nominativo> v = new ArrayVector<Nominativo>(100);
		boolean finito = false;
		try {
			br = new BufferedReader(new FileReader(nomeFile));
			StringTokenizer st = null;
			String linea = null;
			String cognome, nome, prefisso, telefono;
			for ( ; ; ) {
				linea = br.readLine();
				if (linea == null) {
					break;
				}
				st = new StringTokenizer(linea, " -");
				cognome = st.nextToken();
				nome = st.nextToken();
				prefisso = st.nextToken();
				telefono = st.nextToken();
				Nominativo n = new Nominativo(cognome, nome, prefisso, telefono);
				v.add(n);
			}
			finito = true;
		} finally {
			if (br != null) {
				br.close();
			}
		}
		if (finito) {
			svuota();
			for (Nominativo n : v) {
				aggiungi(n);
			}
		}
	}
	
}
