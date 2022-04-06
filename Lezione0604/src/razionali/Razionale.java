package razionali;

public class Razionale 
{	private int numeratore;
	private int denominatore;
	
	public Razionale(int numeratore, int denominatore)
	{	this.numeratore = numeratore;
		this.denominatore = denominatore;
		
		if(this.denominatore < 0)
		{	this.numeratore *= -1;
			this.denominatore *= -1;			
		}
		
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
	{	//DA FARE		
	}

	private int massimoComunDivisore(int a, int b)
	{	for(int v = Math.min(a, b); ;v--)
			if(a % v == 0 && b % v == 0)
				return v;	
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
}
