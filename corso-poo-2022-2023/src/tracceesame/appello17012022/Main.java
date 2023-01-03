package tracceesame.appello17012022;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		String defaultLocation = new File(".").getAbsolutePath();
		System.out.println("Salva i file qui: " + defaultLocation);
		
		File f1 = readAndGetFile();
		File f2 = readAndGetFile();
		
		Set<String> l1 = null;
		Set<String> l2 = null;
		try {
			l1 = getDataFromFile(f1);
			l2 = getDataFromFile(f2);
		} catch (FileNotFoundException e) {
			System.err.println("File non trovati, riprovare.");
			System.exit(0);
		}

		Set<String> ret = paroleDistinte(l1, l2);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String string : ret) {
			i++;
			sb.append(i);
			sb.append(") ");
			sb.append(string);
			sb.append(";\n");
		}
		System.out.println(sb.toString());
		System.out.println("Finito.");
	}

	private static File readAndGetFile() {
		String filename = getFilename();
		return new File(filename);
	}
	
	private static File readAndGetFile(String s) { // usato solo per il debugging
		return new File(s);
	}

	@SuppressWarnings("resource")
	private static String getFilename() {
		System.out.println("Enter filepath: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private static Set<String> getDataFromFile(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		Set<String> l = new HashSet<>();
		String dividers = " .,:;'<>()-_";
		while(s.hasNextLine()) {
			String line = s.nextLine();
			StringTokenizer st = new StringTokenizer(line, dividers);
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				l.add(token);
			}
		}
		
		return l;
	}
	
	private static Set<String> paroleDistinte(Set<String> l1, Set<String> l2) {
		// tutte le parole non comuni ordinate con il MioComparator
		Set<String> ret = new TreeSet<>(MioComparator);
		for (String s : l1) {
			if (!l2.contains(s)) {
				ret.add(s);
			}
		}
		return ret;
	}
	
	static Comparator<String> MioComparator = new Comparator<String>() {
		
		@Override
		public int compare(String s1, String s2) {
			if (s1.length() < s2.length()) return -1;
			if (s1.length() > s2.length()) return 1;
			return s1.compareTo(s2);
		}
	};
	
}