package listaconcatenata;

import java.util.*;

import terminale.Terminale;

class NodoInt {
	
	private int info;
	private NodoInt successivo;

	public NodoInt(int info, NodoInt successivo) {
		this.info = info;
		this.successivo = successivo;
	}

	public NodoInt(int info) {
		this(info, null);
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public NodoInt getSuccessivo() {
		return successivo;
	}

	public void setSuccessivo(NodoInt successivo) {
		this.successivo = successivo;
	}

	public boolean haInfo(int info) {
		return this.info == info;
	}

	public String toString() {
		return "" + info;
	}
}

public class ListaConcatenataInt {
	
	private NodoInt testa;
	private NodoInt coda;
	private int lunghezza;

	private void inizializza() {
		testa = null;
		coda = null;
		lunghezza = 0;
	}

	public ListaConcatenataInt() {
		inizializza();
	}

	public ListaConcatenataInt(ArrayList<Integer> a) {
		inizializza();
		for (int v : a)
			aggiungiInCoda(v);
	}

	public ListaConcatenataInt(int[] a) {
		inizializza();
		for (int v : a)
			aggiungiInCoda(v);
	}

	public void svuota() {
		inizializza();
	}

	public int getLunghezza() {
		return lunghezza;
	}

	public boolean eVuota() {
		return lunghezza == 0;
	}

	public void aggiungiInCoda(int valore) {
		NodoInt nuovoNodo = new NodoInt(valore);
		if (eVuota())
			testa = nuovoNodo;
		else
			coda.setSuccessivo(nuovoNodo);
		coda = nuovoNodo;
		lunghezza++;
	}

	public void aggiungiInTesta(int valore) {
		NodoInt nuovoNodo = new NodoInt(valore, testa);
		testa = nuovoNodo;
		if (coda == null)
			coda = nuovoNodo;
		lunghezza++;
	}

	public String toString() {
		String ret = "[";
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret += corrente;
			if (corrente.getSuccessivo() != null)
				ret += ",";
		}
		ret += "]";
		return ret;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof ListaConcatenataInt))
			return false;
		ListaConcatenataInt l = (ListaConcatenataInt) o;
		if (lunghezza != l.lunghezza)
			return false;
		NodoInt corrente = testa;
		NodoInt correnteL = l.testa;
		while (corrente != null) {
			if (corrente.getInfo() != correnteL.getInfo())
				return false;
			corrente = corrente.getSuccessivo();
			correnteL = correnteL.getSuccessivo();
		}
		return true;
	}

	public ListaConcatenataInt listaInvertita() {
		ListaConcatenataInt ret = new ListaConcatenataInt();
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
			ret.aggiungiInTesta(corrente.getInfo());
		return ret;
	}

	public ArrayList<Integer> adArrayList() {
		ArrayList<Integer> ret = new ArrayList<>();
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
			ret.add(corrente.getInfo());
		return ret;
	}

	public int[] adArray() {
		if (eVuota())
			throw new EccezioneListaVuota();
		int[] ret = new int[lunghezza];
		int posLibera = 0;
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret[posLibera] = corrente.getInfo();
			posLibera++;
		}
		return ret;
	}

	public void rimuoviTesta() {
		if (eVuota())
			throw new EccezioneListaVuota();
		if (lunghezza == 1) {
			svuota();
			return;
		}
		testa = testa.getSuccessivo();
		lunghezza--;
	}

	public void rimuoviCoda() {
		if (eVuota())
			throw new EccezioneListaVuota();
		if (lunghezza == 1) {
			svuota();
			return;
		}
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
			if (corrente.getSuccessivo() == coda) {
				corrente.setSuccessivo(null);
				coda = corrente;
			}
		lunghezza--;
	}

	public void rimuovi(int indice) {
		if (indice < 0 || indice >= lunghezza)
			throw new EccezioneIndiceNonValido();
		if (indice == 0) {
			rimuoviTesta();
			return;
		}
		if (indice == lunghezza - 1) {
			rimuoviCoda();
			return;
		}
		NodoInt corrente = testa;
		for (int i = 1; i <= indice - 1; i++)
			corrente = corrente.getSuccessivo();
		corrente.setSuccessivo(corrente.getSuccessivo().getSuccessivo());
		lunghezza--;
	}

	public void rimuoviPrimo(int info) {
		if (eVuota())
			return;
		if (testa.haInfo(info)) {
			rimuoviTesta();
			return;
		}
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			NodoInt successivo = corrente.getSuccessivo();
			if (successivo != null && successivo.haInfo(info)) {
				corrente.setSuccessivo(successivo.getSuccessivo());
				if (coda == successivo)
					coda = corrente;
				lunghezza--;
				return;
			}
		}
	}

	public int indiceDi(int info) {
		int pos = 0;
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			if (corrente.haInfo(info))
				return pos;
			pos++;
		}
		return -1;
	}

	public boolean contiene(int info) {
		return indiceDi(info) != -1;
	}

	public int get(int indice) {
		if (indice < 0 || indice >= lunghezza)
			throw new EccezioneIndiceNonValido();
		NodoInt corrente = testa;
		for (int i = 1; i <= indice; i++)
			corrente = corrente.getSuccessivo();
		return corrente.getInfo();
	}

	private int sommaDa(NodoInt n) {
		if (n == null)
			return 0;
		return n.getInfo() + sommaDa(n.getSuccessivo());
	}

	public int somma() {
		return sommaDa(testa);
	}

	private int minimoDa(NodoInt n) {
		NodoInt successivoAdN = n.getSuccessivo();
		if (successivoAdN == null)
			return n.getInfo();
		return Math.min(n.getInfo(), minimoDa(successivoAdN));
	}

	public int minimo() {
		if (eVuota())
			throw new EccezioneListaVuota();
		return minimoDa(testa);
	}

	private int massimoDa(NodoInt n) {
		NodoInt successivoAdN = n.getSuccessivo();
		if (successivoAdN == null)
			return n.getInfo();
		return Math.max(n.getInfo(), massimoDa(successivoAdN));
	}

	public int massimo() {
		if (eVuota())
			throw new EccezioneListaVuota();
		return massimoDa(testa);
	}

	private int contaDa(NodoInt n, int info) {
		if (n == null)
			return 0;
		if (n.haInfo(info))
			return 1 + contaDa(n.getSuccessivo(), info);
		return contaDa(n.getSuccessivo(), info);
	}

	public int conta(int info) {
		return contaDa(testa, info);
	}

	// ------------------- esercizi esame ----------------------- //

	private boolean stesseSottoSequenzeDa(NodoInt n) {
		/*
		 * return n == coda || n.getSuccessivo() == coda || n.getInfo() !=
		 * n.getSuccessivo().getSuccessivo().getInfo() &&
		 * stesseSottoSequenzeDa(n.getSuccessivo());
		 */
		if (n == coda || n.getSuccessivo() == coda)
			return true;
		if (n.getInfo() != n.getSuccessivo().getSuccessivo().getInfo()) {
			return false;
		}
		return stesseSottoSequenzeDa(n.getSuccessivo());

	}

	public boolean stesseSottoSequenze() {
		if (lunghezza != 0 && lunghezza % 2 != 0) {
			return false;
		}
		return stesseSottoSequenzeDa(testa);
	}

	// --------------------------------------------------------- //

	private boolean verificaDa(NodoInt n, boolean posizionePari) {
		if (n == null || n.getSuccessivo() == null || n.getSuccessivo().getSuccessivo() == null)
			return true;
		NodoInt successivoDelSuccessivo = n.getSuccessivo().getSuccessivo();
		if (posizionePari && 2 * n.getInfo() >= successivoDelSuccessivo.getInfo())
			return false;
		if (!posizionePari && 3 * n.getInfo() >= successivoDelSuccessivo.getInfo())
			return false;
		return verificaDa(n.getSuccessivo(), !posizionePari);
	}

	public boolean verifica() {
		if (lunghezza < 3)
			return true;
		return verificaDa(testa, true);
	}

	// ------------------ 20/07/2021 --------------------------- //

	private boolean verifica0ePoiPositiviDa(NodoInt n, boolean vistoZero) { // manca caso con 0 assente
		if (n == null)
			return true;
		if (vistoZero && n.getInfo() < 0) {
			return false;
		} else if (!vistoZero && n.getInfo() == 0) {
			vistoZero = true;
		}
		return verifica0ePoiPositiviDa(n.getSuccessivo(), vistoZero);
	}

	public boolean verifica0ePoiPositivi() {
		if (eVuota())
			return false;
		if (lunghezza == 1 && testa.getInfo() != 0)
			return false;
		return verifica0ePoiPositiviDa(testa, false);
	}

	// ------------------- 03/07/2020 ----------------------- //

	private boolean alternatiDa(NodoInt n) {
		if (n == null || n.getSuccessivo() == null)
			return true;
		if (n.getInfo() >= 0 != (n.getSuccessivo().getInfo() >= 0)) {
			return alternatiDa(n.getSuccessivo());
		} else {
			return false;
		}
	}

	public boolean alternati() {
		if (eVuota())
			return true;
		return alternatiDa(testa);
	}

	// ------------------- 23/06/20221 ----------------------- //

	private int numeroScaliniDa(NodoInt n) {
		if (n == null || n.getSuccessivo() == null || n.getSuccessivo().getSuccessivo() == null)
			return 0;
		NodoInt successivo = n.getSuccessivo();
		NodoInt succDelSuccessivo = n.getSuccessivo().getSuccessivo();
		float media = (successivo.getInfo() + succDelSuccessivo.getInfo()) / 2;
		if (n.getInfo() < media) {
			return 1 + numeroScaliniDa(successivo);
		}
		return numeroScaliniDa(successivo);
	}

	public boolean verifica(int x) { // numero scalini minimo
		if (eVuota() && x != 0)
			return false;
		return numeroScaliniDa(testa) >= x;
	}

	// ------------------- 23/07/2020 ----------------------- //

	private int contaElementiDa(NodoInt n, int precedente) {
		if (n == null)
			return 0;
		if (n.getSuccessivo() == null) {
			return (n.getInfo() < precedente) ? 1 : 0;
		}
		int corrente = n.getInfo();
		int successivo = n.getSuccessivo().getInfo();
		if (n.getInfo() < (precedente + successivo) / 2) {
			return 1 + contaElementiDa(n.getSuccessivo(), corrente);
		}
		return contaElementiDa(n.getSuccessivo(), corrente);
	}

	public int contaElementi() {
		if (eVuota())
			return 0;
		return contaElementiDa(testa, 0);
	}

	// ------------------- 11/07/2022 ----------------------- //

	private boolean verificaOrdinamentoDa(NodoInt n, int prec) {
		if (n == null || n.getSuccessivo() == null)
			return true;
		int valore = n.getInfo();
		if (valore >= 0 == prec >= 0) {
			if (prec >= 0) {
				if (valore > prec) {
					return false;
				}
			} else {
				if (valore < prec) {
					return false;
				}
			}
		} else {
			prec = valore;
		}
		return verificaOrdinamentoDa(n.getSuccessivo(), prec);
	}

	public boolean verificaOrdinamento() {
		if (lunghezza < 2)
			return true;
		return verificaOrdinamentoDa(testa.getSuccessivo(), testa.getInfo());
	}

	// ------------------- 28-06-2022 - orale per 30 e lode -----------------------

	public ListaConcatenataInt inversione() {
		if (eVuota())
			return this;
		return inversioneDa(testa);
	}

	private ListaConcatenataInt inversioneDa(NodoInt n) {
		if (n.getSuccessivo() == null) {
			ListaConcatenataInt l = new ListaConcatenataInt();
			l.aggiungiInCoda(n.getInfo());
			return l;
		}
		rimuoviTesta();
		inversioneDa(n.getSuccessivo()).aggiungiInCoda(n.getInfo());
		return this;
	}

}
