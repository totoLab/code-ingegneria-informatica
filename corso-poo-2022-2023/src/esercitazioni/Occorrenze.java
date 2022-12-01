package esercitazioni;

import java.util.*;
import java.io.*;

public class Occorrenze {
	
	private static Map<String, Integer> contaOccorrenze(String path) throws IOException {
		Map<String, Integer> res = new TreeMap();
		File f = new File(path);
		if (!f.exists()) throw new FileNotFoundException();
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, " ,.?-;\"\'\t", false);
			String word = st.nextToken().toUpperCase();
			if (!res.containsKey(word)) {
				res.put(word, 0);
			}
			res.put(word, res.get(word)+1);
		}
		return res;
	}	
	
	// rifarlo con StringaIF 
}
