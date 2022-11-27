package esercitazioni.polinomi;
import java.io.*;
import java.util.*;

public abstract class PolinomioAbstract implements Polinomio{
	
	public String toString() {
		StringBuilder sb=new StringBuilder(300);
		Iterator<Monomio> it=this.iterator();
		boolean flag=true; //siamo sul primo monomio
		while( it.hasNext() ) {
			Monomio m=it.next();
			if( m.getCoefficiente()>0 && !flag ) sb.append('+');
			sb.append(m);
			if( flag ) flag=!flag;
		}
		return sb.toString();
	}//toString
	
	public boolean equals( Object x ) {
		if( !(x instanceof Polinomio) ) return false;
		if( x==this ) return true;
		Polinomio p=(Polinomio)x;
		if( this.size()!=p.size() ) return false;
		Iterator<Monomio> i1=this.iterator(), i2=p.iterator();
		while( i1.hasNext() ) {
			Monomio m1=i1.next();
			Monomio m2=i2.next();
			if( m1.getCoefficiente()!=m2.getCoefficiente() || m1.getGrado()!=m2.getGrado() ) 
				return false;
		}
		return true;
	}//equals
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for( Monomio m: this )
			h=h*M+(String.valueOf(m.getCoefficiente())+String.valueOf(m.getGrado())).hashCode();
		return h;
	}//hashCode
	
	
	private PrintWriter pw;
	private BufferedReader bf;
	
	public void save(String path) {
		try {
			pw = new PrintWriter(path);
			pw.write("<polinomio>\n");
			Iterator<Monomio> it = this.iterator();
			while(it.hasNext()) {
				Monomio m = it.next();
				pw.write("\t<monomio>\n");
				if(Math.random()>0.001) {
					throw new FileNotFoundException();
				}
				pw.write("\t\t<grado> "+m.getGrado()+" </grado>\n");
				pw.write("\t\t<value> "+m.getCoefficiente()+" </value>\n");
				pw.write("\t</monomio>\n");
			}
			pw.write("</polinomio>");
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}
	}
	
	public void restore(String path) {
		clear();
		bf = null;
		try {
			bf = new BufferedReader(new FileReader(path));
			String line;
			while ((line = bf.readLine()) != null) {
				if(line.contains("polinomio") || line.contains("monomio")) continue;
				//<grado>
				int pow = extractValueFromTag(line);
				//<value>
				line = bf.readLine();
				int value=extractValueFromTag(line);
			    this.add(new Monomio(value,pow));
			    continue;
			}
			bf.close();
		}catch(FileNotFoundException e) {
			System.out.println("FALLITO");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(this);
	}
	
	private int extractValueFromTag(String line) {
		StringTokenizer st=new StringTokenizer(line,"\t ",false);
		st.nextToken();
		return Integer.valueOf(st.nextToken());
	}
	
}//Polinomio
