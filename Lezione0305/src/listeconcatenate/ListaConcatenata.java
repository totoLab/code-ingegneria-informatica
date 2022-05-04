package listeconcatenate;

class Nodo<E>
{	private E info;
	private Nodo<E> successivo;
	
	public Nodo(E info, Nodo<E> successivo)
	{	this.info = info;
		this.successivo = successivo;		
	}
	
	public Nodo(E info)
	{	this(info,null);		
	}

	public E getInfo() 
	{	return info;
	}

	public void setInfo(E info) 
	{	this.info = info;
	}

	public Nodo<E> getSuccessivo() 
	{	return successivo;
	}

	public void setSuccessivo(Nodo<E> successivo) 
	{	this.successivo = successivo;
	}
	
	public boolean haInfo(E info)
	{	return this.info.equals(info);		
	}
	
	public String toString()
	{	return info.toString();		
	}	
}

public class ListaConcatenata<E>
{	private Nodo<E> testa;
	private Nodo<E> coda;
	private int lunghezza;
	
	private void inizializza()
	{	testa = null;
		coda = null;
		lunghezza = 0;		
	}
	
	public ListaConcatenata()
	{	inizializza();		
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
	
	public void aggiungiInCoda(E valore)
	{	Nodo<E> nuovoNodo = new Nodo<E>(valore);
		if(eVuota())
			testa = nuovoNodo;
		else
			coda.setSuccessivo(nuovoNodo);
		coda = nuovoNodo;
		lunghezza++;			
	}
	
	public void aggiungiInTesta(E valore)
	{	Nodo<E> nuovoNodo = new Nodo<E>(valore,testa);
		testa = nuovoNodo;
		if(coda == null)
			coda = nuovoNodo;
		lunghezza++;
	}
	
	public String toString()
	{	String ret = "[";
		for(Nodo<E> corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
		{	ret += corrente;
			if(corrente.getSuccessivo() != null)
				ret += ",";
		}
		ret += "]";
		return ret;		
	}
	
	public boolean equals(Object o)
	{	if(o == null)
			return false;
		if(o == this)
			return true;
		if(!(o instanceof ListaConcatenata<?>))
			return false;
		ListaConcatenata<E> l = (ListaConcatenata<E>)o;
		if(lunghezza != l.lunghezza)
			return false;
		Nodo<E> corrente = testa;
		Nodo<E> correnteL = l.testa;
		while(corrente != null)
		{	if(!corrente.getInfo().equals(correnteL.getInfo()))
				return false;
			corrente = corrente.getSuccessivo();
			correnteL = correnteL.getSuccessivo();
		}
		return true;
	}
	
	public ListaConcatenata<E> listaInvertita()
	{	ListaConcatenata<E> ret = new ListaConcatenata<>();
		for(Nodo<E> corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
			ret.aggiungiInTesta(corrente.getInfo());
		return ret;		
	}
	
	public void rimuoviTesta()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		if(lunghezza == 1)
		{	svuota();
			return;
		}
		testa = testa.getSuccessivo();
		lunghezza--;
	}
	
	public void rimuoviCoda()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		if(lunghezza == 1)
		{	svuota();
			return;
		}
		for(Nodo<E> corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
			if(corrente.getSuccessivo() == coda)
			{	corrente.setSuccessivo(null);
				coda = corrente;				
			}
		lunghezza--;
	}	
	
	public void rimuovi(int indice)
	{	if(indice < 0 || indice >= lunghezza)
			throw new EccezioneIndiceNonValido();
		if(indice == 0)
		{	rimuoviTesta();
			return;			
		}
		if(indice == lunghezza - 1)
		{	rimuoviCoda();
			return;			
		}
		Nodo<E> corrente = testa;
		for(int i = 1; i <= indice - 1; i++)
			corrente = corrente.getSuccessivo();
		corrente.setSuccessivo(corrente.getSuccessivo().getSuccessivo());
		lunghezza--;
	}
	
	public void rimuoviPrimo(E info)
	{	if(eVuota())
			return;
		if(testa.haInfo(info))
		{	rimuoviTesta();
			return;			
		}
		for(Nodo<E> corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
		{	Nodo<E> successivo = corrente.getSuccessivo();
			if(successivo != null && successivo.haInfo(info))
			{	corrente.setSuccessivo(successivo.getSuccessivo());
				if(coda == successivo)
					coda = corrente;
				lunghezza--;
				return;				
			}
		}
	}
	
	public int indiceDi(E info)
	{	int pos = 0;
		for(Nodo<E> corrente = testa; corrente != null; corrente = corrente.getSuccessivo())
		{	if(corrente.haInfo(info))
				return pos;
			pos++;
		}
		return -1;
	}
	
	public boolean contiene(E info)
	{	return indiceDi(info) != -1;
	}
	
	public E get(int indice)
	{	if(indice < 0 || indice >= lunghezza)
			throw new EccezioneIndiceNonValido();
		Nodo<E> corrente = testa;
		for(int i = 1; i <= indice; i++)
			corrente = corrente.getSuccessivo();
		return corrente.getInfo();
	}
}