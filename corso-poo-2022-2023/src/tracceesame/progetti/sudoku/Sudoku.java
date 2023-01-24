package tracceesame.progetti.sudoku;

import java.util.*;

import poo.ricorsione.Backtracking;

public class Sudoku extends Backtracking<CellaSudoku, Integer>  {

	CellaSudoku[][] griglia;
	int numeroSoluzioni = 0, maxSoluzioni = 1;
	
	public Sudoku() {
		this.griglia = new CellaSudoku[9][9];
		for (int i = 0; i < griglia.length; i++) {
			for (int j = 0; j < griglia.length; j++) {
				griglia[i][j] = new CellaSudoku(i, j, 0);
			}
		}
	}
	
	private void imposta(int i, int j, int v) {
		CellaSudoku c = new CellaSudoku(i, j, v);
		if (assegnabile(c, v)) {
			griglia[i][j].setValore(v);
		}
	}
	
	@Override
	protected boolean assegnabile(CellaSudoku c, Integer i) {
		return griglia[c.getI()][c.getJ()].getValore() == 0 && numeriDisponibili(c).contains(i);
	}
	
	@Override
	protected void assegna(CellaSudoku c, Integer i) {
		imposta(c.getI(), c.getJ(), i);
	}

	@Override
	protected void deassegna(CellaSudoku c, Integer i) {
		imposta(c.getI(), c.getJ(), 0);
	}

	@Override
	protected void scriviSoluzione(CellaSudoku p) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (CellaSudoku[] riga : griglia) {
			sb.append("    ");
			sb.append(Arrays.toString(riga));
			sb.append(",\n");
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	@Override
	protected boolean esisteSoluzione(CellaSudoku p) {
		for (CellaSudoku[] riga : griglia) {
			for (CellaSudoku elemento : riga) {
				if (elemento.getValore() == 0) return false;
			}
		}
		numeroSoluzioni++;
		return true;
	}
	
	@Override
	protected boolean ultimaSoluzione(CellaSudoku p) {
		return numeroSoluzioni == maxSoluzioni;
	}

	@Override
	protected List<CellaSudoku> puntiDiScelta() {
		List<CellaSudoku> ret = new LinkedList<>();
		for (CellaSudoku[] riga : griglia) {
			for (CellaSudoku elemento : riga) {
				if (elemento.getValore() == 0) {
					ret.add(elemento);
				}
			}
		}
		return ret;
	}

	@Override
	protected Collection<Integer> scelte(CellaSudoku c) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 1; i <= 9; i++) { ret.add(i); }
		return ret;
	}

	@Override
	protected void risolvi() {
		tentativo(puntiDiScelta(), griglia[0][0]);
	}
	
	private Collection<Integer> numeriDisponibili(CellaSudoku c) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 1; i <= 9; i++) { ret.add(i); }
		
		// elementi riga c.i
		for (int j = 0; j < griglia.length; j++) {
			Integer elemento = griglia[c.getI()][j].getValore();
			if (elemento != 0 && ret.contains(elemento)) {
				ret = removeFromArrayList(ret, elemento); // elimina elemento, non elemento a quell'indice, possibile errore dato che il paramentro è un intero
			} 
		}
		
		// elementi colonna c.j
		for (int i = 0; i < griglia.length; i++) {
			Integer elemento = griglia[i][c.getJ()].getValore();
			if (elemento != 0 && ret.contains(elemento)) {
				ret = removeFromArrayList(ret, elemento); 
			} 
		}
		
		// elementi quadratino
		CellaSudoku altoSx = getASxCoordinates(c); // TODO rename
		for (int i = altoSx.getI(); i < altoSx.getI() + 3; i++) {
			for (int j = altoSx.getJ(); j < altoSx.getJ() + 3; j++) {
				Integer elemento = griglia[i][j].getValore();
				if (elemento != 0 && ret.contains(elemento)) {
					ret = removeFromArrayList(ret, elemento); 
				}
			}
		}
		
		return ret;
	}
	
	private ArrayList<Integer> removeFromArrayList(ArrayList<Integer> ret, Integer elemento) {
		for (int i = 0; i < ret.size(); i++) {
			if (ret.get(i) == elemento) {
				ret.remove(i);
				break;
			}
		}
		return ret;
	}

	private CellaSudoku getASxCoordinates(CellaSudoku c) {
		int i = (c.getI() / 3) * 3;
		int j = (c.getJ() / 3) * 3;
		return griglia[i][j];
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.scriviSoluzione(new CellaSudoku(0, 0, 0));
		s.risolvi();
		s.scriviSoluzione(new CellaSudoku(0, 0, 0));

		System.out.println("done");
	}
	
}
