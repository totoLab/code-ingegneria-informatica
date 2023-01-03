package poo;

import java.io.*;
import java.util.*;

class MioComparator implements Comparator<String> {
	public int compare(String s1, String s2) {
		if (s1.length() < s2.length()) return -1;
		if (s1.length() > s2.length()) return 1; 
		return s1.compareTo(s2); // a parità di lunghezza usa il confronto di default
	}
}

class Funzioni {
	public static int primoTipoDiConfronto(String s1, String s2) {
		return s1.compareTo(s2);
	}
	
	public static int secondoTipoDiConfronto(String s1, String s2) {
		if (s1.length() < s2.length()) return -1;
		if (s1.length() > s2.length()) return 1; 
		return s1.compareTo(s2);
	}
}

class InterfacceFunzionali {

	public static void main(String[] args) {
		
		// #1
		List<String> ls = Arrays.asList("casa", "tetto", "albero", "cassaforte");
		Collections.sort(ls);

		// #2
		Comparator<String> cc = new MioComparator();
		Collections.sort(ls, cc);
		
		// #3
		Comparator<String> cs = new Comparator<String>() {
			public int compare(String s1, String s2) {
				if (s1.length() < s2.length()) return -1;
				if (s1.length() > s2.length()) return 1; 
				return s1.compareTo(s2);
			}
		};
		Collections.sort(ls, cs);
		
		// #4 - lambda expression
		Comparator<String> cl = (s1, s2) -> {
			if (s1.length() < s2.length()) return -1;
			if (s1.length() > s2.length()) return 1; 
			return s1.compareTo(s2);
		};
		Collections.sort(ls, cl);
		
		// #5
		Comparator<String> cf = Funzioni::primoTipoDiConfronto;
		Collections.sort(ls, cf);

		Funzioni ff = new Funzioni();
		
	}
}

public class LezioneRandomSuCoseCarine {
	
	public static void inputOutput() throws IOException {
		InputStream source = new FileInputStream("../../src/poo/f1.dat");
		OutputStream dest = new FileOutputStream("f2.dat");
		
		int dato;
		for (;;) {
			if (source.available() == 0) break;
			dato = source.read();
			dest.write(dato);
		}
		source.close();
		dest.close();
	}
	
	public static void esempioConFile(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Manca chiave");
			System.exit(-1);
		}
		int chiave = Integer.parseInt(args[0]);
		InputStream source = new BufferedInputStream(new FileInputStream("source.dat"));
		OutputStream dest = new BufferedOutputStream(new FileOutputStream("dest.dat"));
		
		int dato;
		for (;;) {
			if (source.available() == 0) break;
			dato = source.read();
			
			dest.write(crittografa(dato, chiave));
		}
		source.close();
		dest.close();
	}

	private static byte crittografa(int dato, int chiave) {
		return (byte) (dato + chiave);
	}
	
	private void writeStringsToFile() throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("file.dat"));
		System.out.print("Inserisci ");
		Scanner sc = new Scanner(System.in);
		
		int x;
		for (;;) {
			x = sc.nextInt();
			if (x == 0) break;
			dos.writeInt(x);
		}
		dos.close();
		DataInputStream dis = new DataInputStream(new FileInputStream("file.dat"));
		for (;;) {
			try {
				x = dis.readInt();
			} catch (EOFException e) {
				break;
			}
		}
		dis.close();

	}
	
	static boolean esiste(String nome, int x) throws IOException {
		RandomAccessFile f = new RandomAccessFile(nome, "r");
		int inf = 0; int sup = (int)(f.length()/4) - 1;
		boolean result = false;
		for (;;) {
			if (inf > sup) break;
			int med = (inf + sup) / 2;
			f.seek(med * 4);
			int elem = f.readInt();
			if (elem == x) {
				result = true;
				break;
			}
			if (elem > x) {
				sup = med - 1;
			} else {
				inf = med + 1;
			}
		}
		f.close();
		return result;
	}
	
	static void mostraContenutoFile(String nome) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(nome));
		DataInputStream dis = new DataInputStream(in);
		for (;;) {
			try {
				int x = dis.readInt();
				System.out.println(x);
			} catch (Exception e) {
				break;
			}
		}
		dis.close();
	}
	
	static void inserisci(String nome, int x) throws IOException {
		mostraContenutoFile(nome);
		RandomAccessFile raf = new RandomAccessFile(nome, "r");
		DataOutputStream tmp = new DataOutputStream(new FileOutputStream("tmp"));
		long pos = 0;
		int y = 0;
		boolean flag = false;
		while (pos < raf.length() && !flag) {
			y = raf.readInt();
			if (y > x) {
				flag = true;
			} else {
				tmp.write(y);
			}
			pos = raf.getFilePointer();
		} 
		tmp.writeInt(x);
		if (flag) {
			for (;;) {
				tmp.writeInt(y);
				pos = raf.getFilePointer();
				if (pos == raf.length()) {
					break;
				} else {
					y = raf.readInt();
				}
			}
		}
		tmp.close();
		raf.close();
		
		mostraContenutoFile("tmp");
		File f = new File(nome);
		f.delete();
		File ff = new File("tmp");
		ff.renameTo(f);
	}
	
	public static void main(String[] args) {
		
	}
	
}
