package listeconcatenate;

import java.util.*;

class NodoInt
{	private int info;
	private NodoInt successivo;
	
	public NodoInt(int info, NodoInt successivo)
	{	this.info = info;
		this.successivo = successivo;		
	}
	
	public NodoInt(int info)
	{	this(info,null);		
	}

	public int getInfo() 
	{	return info;
	}

	public void setInfo(int info) 
	{	this.info = info;
	}

	public NodoInt getSuccessivo() 
	{	return successivo;
	}

	public void setSuccessivo(NodoInt successivo) 
	{	this.successivo = successivo;
	}
	
	public boolean haInfo(int info)
	{	return this.info == info;		
	}
	
	public String toString()
	{	return ""+info;		
	}
		
}

public class ListaConcatenataInt 
{	private NodoInt testa;
	private NodoInt coda;
	private int lunghezza;
	
	private void inizializza()
	{	testa = null;
		coda = null;
		lunghezza = 0;		
	}
	
	public ListaConcatenataInt()
	{	inizializza();		
	}
	
	public ListaConcatenataInt(ArrayList<Integer> a)
	{	inizializza();
		for(int v : a)
			aggiungiInCoda(v);
	}
	
	public ListaConcatenataInt(int[] a)
	{	inizializza();
		for(int v : a)
			aggiungiInCoda(v);
	}
	
	public void svuota()
	{	inizializza();		
	}
	
	public int getLunghezza()
	{	return lunghezza;		
	}
	
	public boolean eVuota()
	{	return lunghezza == 0;		
	}
	
	public void aggiungiInCoda(int valore)
	{	NodoInt nuovoNodo = new NodoInt(valore);
		if(eVuota())
			testa = nuovoNodo;
		else
			coda.setSuccessivo(nuovoNodo);
		coda = nuovoNodo;
		lunghezza++;			
	}
	
	public void aggiungiInTesta(int valore) {
		NodoInt nuovoNodo = new NodoInt(valore, testa);
		testa = nuovoNodo;
		if (coda == null) {
			coda = nuovoNodo;
		lunghezza++;
		}
	}
	
	public String toString() {
		String ret = "[";
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret += corrente.toString();
			if (corrente.getSuccessivo() != null) {
				ret += ", ";
			}
		}
		ret += "]";
		return ret;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof ListaConcatenataInt)) {
			return false;
		}
		
		ListaConcatenataInt l = (ListaConcatenataInt) o;
		if (lunghezza != l.lunghezza) {
			return false;
		}
		NodoInt corrente = testa;
		NodoInt correnteL = l.testa;
		while (corrente != null) {
			if (corrente.getInfo() != correnteL.getInfo()) {
				return false;
			}
			corrente = corrente.getSuccessivo();
			correnteL = correnteL.getSuccessivo();
		}
		return true;
	}
	
	public ListaConcatenataInt listaInvertita() {
		ListaConcatenataInt ret = new ListaConcatenataInt();
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret.aggiungiInTesta(corrente.getInfo());
		}
		return ret;
	}
	
	public ArrayList<Integer> adArrayList() {
		ArrayList<Integer> ret = new ArrayList<>();
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret.add(corrente.getInfo());
		}
		return ret;
	}
	
	public int[] adArray() {
		if (eVuota()) throw new EccezioneListaVuota();
		int[] ret = new int[lunghezza];
		int posLibera = 0;
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			ret[posLibera] = corrente.getInfo();
			posLibera++;
		}
		return ret;
	}
	
	public void rimuoviTesta() {
		if (eVuota()) {
			throw new EccezioneListaVuota();
		}
		if (lunghezza == 1) {
			svuota();
			return;
		}
		testa = testa.getSuccessivo();
		lunghezza--;
	}
		
	public int indiceDi(int info) {
		int pos = 0;
		for (NodoInt corrente = testa; corrente != null; corrente = corrente.getSuccessivo()) {
			if (corrente.haInfo(info)) {
				return pos;
			}
			pos++;
		}
		return -1;
	}
	
	public boolean contiene(int info) {
		return indiceDi(info) != -1;
	}
	
}
