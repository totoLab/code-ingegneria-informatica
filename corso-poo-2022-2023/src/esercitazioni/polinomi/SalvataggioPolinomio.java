package esercitazioni.polinomi;

import java.util.*;
import java.io.*;

public class SalvataggioPolinomio {

	public void save(PolinomioLL p, String path) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		try {
			StringBuilder s = new StringBuilder();
			s.append("<polinomio>\n");
			for (Monomio m : p) {
				s.append("\t<monomio>\n");
				s.append("\t\t<grado>" + m.getCoefficiente() + "</grado>\n");
				s.append("\t\t<value>" + m.getGrado() + "</value>\n");
				s.append("\t</monomio>\n");
			}
			s.append("</polinomio>\n");
			
			pw.write(s.toString());
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}		
	}
	
	public Polinomio read(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Polinomio p = new PolinomioLL();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains("polinomio") || line.contains("monomio")) continue;
				int pow = valueFromString(line);
				line = br.readLine();
				int coeff = valueFromString(line);
				
				Monomio m = new Monomio(coeff, pow);
				p.add(m);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return p;
	}
	
	private int valueFromString(String s) {
		StringTokenizer st = new StringTokenizer(s, "\t><", false);
		st.nextToken();
		return Integer.valueOf(st.nextToken());
	}
}
