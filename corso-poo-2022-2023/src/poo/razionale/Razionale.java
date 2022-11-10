package poo.razionale;

import poo.util.Mat;

public class Razionale implements Comparable<Razionale> {

	private static int mcd(int x, int y) {
		do {
			int r=x%y;
			x=y; y=r;
		} while(y!=0);
		return x;
	}
	
	private final int NUM,DEN;
	
	private static int contatore=0;
	
	public static int getContatore() {
		return contatore;
	}
	
	/*public Razionale(int num, int den) {
		if (den==0) {
			System.out.println("Errore");
			System.exit(-1);
		}
		int cd = mcd(num,den);
		this.NUM = num/cd;
		this.DEN = den/cd;
	}*/
	public Razionale(int num, int den) {
		if (den==0) {
			System.out.println("Errore");
			System.exit(-1);
		}
		if (num!=0) {
			int n=Math.abs(num), d=Math.abs(den);
			int cd = mcd(n,d);
			num=num/cd; den=den/cd;
		}
		if (den<0) {num*=-1;den*=-1;}
		
		this.NUM = num;
		this.DEN = den;
		contatore++;
	}
	
	public Razionale(int num) {
		this(num,1);
	}
	
	public Razionale(Razionale r) {
		//this(r.NUM,r.DEN);
		if (r==null) {
			System.out.println("Errore");
			System.exit(-1);
		}
		this.NUM = r.NUM;
		this.DEN = r.DEN;
		contatore++;
	}
	
	public int getNum() {
		return NUM;
	}
	
	public int getDen() {
		return DEN;
	}
	
	public Razionale add(Razionale r) {
		int mcm = (r.DEN*DEN)/mcd(r.DEN,DEN);
		int num=(mcm/DEN)*NUM+(mcm/r.DEN)*r.NUM;
		return new Razionale(num,mcm);
	}
	
	public Razionale sub(Razionale r) {
		int mcm = (r.DEN*DEN)/mcd(r.DEN,DEN);
		int num=(mcm/DEN)*NUM-(mcm/r.DEN)*r.NUM;
		return new Razionale(num,mcm);
	}
	
	public Razionale mul(Razionale r) {
		return new Razionale(NUM*r.NUM,DEN*r.DEN);
	}
	
	public Razionale mul(int s) {
		return new Razionale(s*NUM,DEN);
	}
	
	public String toString() {
		if (NUM==0) return "0 ["+contatore+"]";
		if (DEN==1) return NUM+"["+contatore+"]";
		return NUM+"/"+DEN+"["+contatore+"]";
	}
	
	protected void finalize() {
		contatore--;
	}
	
	@Override
	public int compareTo(Razionale o) {
		Razionale r = (Razionale)o;
		int mcm = Mat.mcm(this.DEN, r.DEN);
		int n1=(mcm/this.DEN)*this.NUM;
		int n2=(mcm/r.DEN)*r.NUM;
		if (n1<n2) return -1;
		if (n1>n2) return 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + DEN;
		result = prime * result + NUM;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Razionale other = (Razionale) obj;
		if (DEN != other.DEN)
			return false;
		if (NUM != other.NUM)
			return false;
		return true;
	}
	
	
}
