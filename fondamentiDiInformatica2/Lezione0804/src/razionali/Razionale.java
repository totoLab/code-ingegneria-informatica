package razionali;

public class Razionale implements Comparable<Razionale>
{	private int numeratore;
	private int denominatore;
	
	public Razionale(int numeratore, int denominatore)
	{	this.numeratore = numeratore;
		this.denominatore = denominatore;
		
		if(this.denominatore < 0)
		{	this.numeratore *= -1;
			this.denominatore *= -1;			
		}
		
		if(denominatore == 0)
			throw new EccezioneDenominatoreZero();
		
		semplifica();	
	}
	
	public Razionale(int n)
	{	numeratore = n;
		denominatore = 1;
		//ALTERNATIVA:
		//this(n,1);		
	}
	
	public Razionale(Razionale r)
	{	numeratore = r.numeratore;
		denominatore = r.denominatore;
		//ALTERNATIVA:
		//this(r.numeratore,r.denominatore);
	}
	
	public Razionale()
	{	numeratore = 0;
		denominatore = 1;
		//ALTERNATIVA:
		//this(0,1);
		//ALTERNATIVA:
		//this(0);
	}
	
	private void semplifica()
	{	if(numeratore == 0)
			denominatore = 1;
		else
		{	int mcd = massimoComunDivisore(Math.abs(numeratore),denominatore);
			numeratore /= mcd;
			denominatore /= mcd;
		}
	}

	private int massimoComunDivisore(int a, int b)
	{	for(int v = Math.min(a, b); ;v--)
			if(a % v == 0 && b % v == 0)
				return v;	
	}
	
	public int getNumeratore()
	{	return numeratore;		
	}
	
	public int getDenominatore()
	{	return denominatore;		
	}
	
	public void moltiplica(Razionale r)
	{	numeratore *= r.numeratore;
		denominatore *= r.denominatore;
		semplifica();
	}
	
	public void moltiplica(int x)
	{	moltiplica(new Razionale(x));
		//ALTERNATIVA:
		//numeratore *= x;
		//semplifica();
	}
	
	public Razionale reciproco()
	{	return new Razionale(denominatore,numeratore);		
	}
	
	public void dividi(Razionale r)
	{	moltiplica(r.reciproco());		
	}
	
	public void aggiungi(Razionale r)
	{	numeratore = numeratore * r.denominatore + denominatore * r.numeratore;
		denominatore *= r.denominatore;
		semplifica();		
	}
	
	public void sottrai(Razionale r)
	{	Razionale menoR = new Razionale(-r.numeratore,r.denominatore);
		aggiungi(menoR);		
	}
	
	public String toString()
	{	if(denominatore == 1)
			return ""+numeratore;
		return numeratore+"/"+denominatore;
	}
	
	public boolean equals(Object o)
	{	if(o == null)
			return false;
		if(o == this)
			return true;
		if(!(o instanceof Razionale))
			return false;
		Razionale r = (Razionale)o;
		return numeratore == r.numeratore && denominatore == r.denominatore;
	}
	
	public int compareTo(Razionale r)
	{	int sinistra = numeratore * r.denominatore;
		int destra = denominatore * r.numeratore;
		if(sinistra < destra)
			return -1;
		if(sinistra > destra)
			return 1;
		return 0;		
	}
	
	public static Razionale prodotto(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.moltiplica(r2);
		return ret;		
	}
	
	public static Razionale somma(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.aggiungi(r2);
		return ret;		
	}

	public static Razionale differenza(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.sottrai(r2);
		return ret;		
	}
	
    public static Razionale rapporto(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.dividi(r2);
		return ret;		
	}
}
