package sfidaHashing;

import java.text.*;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingPuzzle {
	
	static AtomicBoolean canReturn = new AtomicBoolean(false);
	
	static String alphabet = ":;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]";
	static int D = 7;
	static String S = "SisOp-CorsoB-Hashing-Puzzle-";

	
	public static void main(String[] args) {
		
		System.out.println("Test started!");
		long start = System.nanoTime();
		
		initMultiThreading(
				 alphabet.length()
				); // one thread per letter of the alphabet
		
		long end = System.nanoTime();
		System.out.println("Test ETA: " + convertTime(start, end) + "s");
		
	}
	
	private static String convertTime(long start, long end) {
		double time = (end - start) / Math.pow(10, 9);
		DecimalFormat df = new DecimalFormat("#.###");
		return df.format(time);
	}
	
	private static void initMultiThreading(int n) {
		Finder[] finders = new Finder[n];
		ThreadGroup group = new ThreadGroup("finders");
		
		for (int i = 0; i < finders.length; i++) {
			String letterToFix = String.valueOf(alphabet.charAt(i));
			finders[i] = new Finder(group, "t" + String.valueOf(i + 1), letterToFix);
			finders[i].start();
		}
		
		int i = 0;
		boolean found = false;
		Finder current;
		do {
			current = finders[i];
			try {
				found = current.getFound();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Thread " + current.getId() + " was interrupted");
			}
			i++;
		} while (i < n && !found);
		if (found) System.out.println("A string that gives the corrent hashing is '" + S + current.getResult() + "'");
		else System.out.println(":(");
	}
	

	public static boolean hashAndTest(String testString) {
		DigestUtils hasher = new DigestUtils("SHA3-256");
		return hasher.digestAsHex(S + testString)
				.startsWith(generateDString(D));
	}
	
	private static String generateDString(int d) {
		return "0".repeat(d);
	}	 
	
}
