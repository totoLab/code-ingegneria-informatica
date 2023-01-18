package tracceesame.appello02092022;

public class LidoBalneareConcreto extends AbstractLidoBalneare {

	Posizione[][] lido;
	
	public LidoBalneareConcreto(int file, int ombrelloniPerFila) {
		super(file, ombrelloniPerFila);
		this.lido = new Posizione[file][ombrelloniPerFila];
	}

	@Override
	public void liberaOmbrellone(Posizione p) throws IllegalStateException {
		if (eLibero(p)) throw new IllegalStateException();
		lido[p.i][p.j] = null;
	}

	@Override
	public boolean eLibero(Posizione p) {
		return lido[p.i][p.j] == null;
	}

	@Override
	public Posizione[] ombrelloniOccupati() { // override superfluo e non molto più efficiente della controparte dell'interface
		Posizione[] occupati = new Posizione[file * ombrelloniPerFila];
		int cont = 0;
		for (int i = 0; i < file; i++) {
			for (int j = 0; j < ombrelloniPerFila; j++) {
				Posizione corrente = new Posizione(i, j);
				if (!eLibero(corrente)) {
					occupati[cont] = corrente;
					cont++;
				}
			}
		}
		return riduciArray(occupati);
	}

	@Override
	public Posizione[] ombrelloniLiberi() { // override superfluo e non molto più efficiente della controparte dell'interface
		Posizione[] liberi = new Posizione[file * ombrelloniPerFila];
		int cont = 0;
		for (int i = 0; i < file; i++) {
			for (int j = 0; j < ombrelloniPerFila; j++) {
				Posizione corrente = new Posizione(i, j);
				if (eLibero(corrente)) {
					liberi[cont] = corrente;
					cont++;
				}
			}
		}
		return riduciArray(liberi);
	}

	@Override
	public void prenotaOmbrellone(Posizione p) throws IllegalStateException {
		if (!eLibero(p)) throw new IllegalStateException();
		lido[p.i][p.j] = new Posizione(p.i, p.j);
	}
	
	private Posizione[] riduciArray(Posizione[] array) {
		int cont = 0;
		for (int i = 0; i < array.length && array[i] != null; i++) {
			cont++;
		}
		
		return riduciArray(array, cont);
	}
	
	private Posizione[] riduciArray(Posizione[] array, int ultimoIndice) {
		Posizione[] ret = new Posizione[ultimoIndice];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = array[i];
		}
		return ret;
	}

}
