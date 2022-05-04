import java.util.*;

public class GestioneParentele {
	
	private ArrayList<String> parentele;
	
	public String nome(int i) {
		return parentele.get(i);
	}
	
	public int indicePadre(int i) {
		return 2 * i + 1;
	}
	
	public int indiceMadre(int i) {
		return 2 * i + 2;
	}
	
	public String nomePadre(int i) {
		return nome(indicePadre(i));
	}
	
	public String nomeMadre(int i) {
		return nome(indiceMadre(i));
	}
	
	public boolean genitoriMemorizzati(int i) {
		return indiceMadre(i) < parentele.size();
	}
	
	public ArrayList<String> nomiAscendenti(int i) {
		ArrayList<String> ret = new ArrayList<>();
		if (!genitoriMemorizzati(i)) {
			return ret;
		}
		ret.add(nomePadre(i));
		ret.add(nomeMadre(i));
		ret.addAll(nomiAscendenti(indicePadre(i)));
		ret.addAll(nomiAscendenti(indiceMadre(i)));
		return ret;
	}

}
