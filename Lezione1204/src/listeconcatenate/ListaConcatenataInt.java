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
		//DA FARE
		return false;
	}
}
