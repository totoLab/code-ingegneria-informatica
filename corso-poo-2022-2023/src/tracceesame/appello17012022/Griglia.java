package tracceesame.appello17012022;

import java.util.*;
import poo.ricorsione.*;

class Cella {
	
	int i, j;
	
	public Cella(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		final int prime = 431;
		int h = 0;
		h = h + i * prime;
		h = h + j * prime;
		return h;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof Cella)) return false;
		
		Cella c = (Cella) o;
		return this.i == c.i && this.j == c.j;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(i);
		sb.append(", ");
		sb.append(j);
		sb.append(")");
		return sb.toString();
	}
	
}

enum Colore {bianco, grigio, rosso, verde};

public class Griglia extends Backtracking<Cella, Colore> {

	private Colore[][] griglia;
	private int maxSoluzioni, numeroSoluzioni = 0;
	
	public Griglia(int n, int m, int maxSoluzioni) {
		this.maxSoluzioni = maxSoluzioni;
		griglia = new Colore[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				griglia[i][j] = null;
			}
		}
	}

	@Override
	protected boolean assegnabile(Cella p, Colore s) {
		int rigaMin = Math.max(p.i - 1, 0);
		int rigaMax = Math.min(p.i + 1, griglia.length - 1);
		int colonnaMin = Math.max(p.j - 1, 0);
		int colonnaMax = Math.min(p.j + 1, griglia[0].length - 1);
		for (int r = rigaMin; r <= rigaMax; r++) {
			for (int c = colonnaMin; c <= colonnaMax; c++) {
				if ( (!(r == p.i && c == p.j)) &&
						s.equals(griglia[r][c]))
					return false;
			}
		}
		return true;
	}

	@Override
	protected void assegna(Cella p, Colore s) {
		griglia[p.i][p.j] = s;
	}

	@Override
	protected void deassegna(Cella p, Colore s) {
		griglia[p.i][p.j] = null;
	}
	
	@Override
	protected void scriviSoluzione(Cella p) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int i = 0; i < griglia.length; i++) {
			sb.append(Arrays.toString(griglia[i]));
			sb.append(",\n");
		}
		sb.append("]\n");
		System.out.println(sb.toString());
	}

	@Override
	protected List<Cella> puntiDiScelta() {
		List<Cella> p = new ArrayList<>();
		for (int i = 0; i < griglia.length; i++) {
			for (int j = 0; j < griglia[0].length; j++) {
				p.add(new Cella(i, j));
			}
		}
		return p;
	}

	@Override
	protected Collection<Colore> scelte(Cella p) {
		Colore[] colori = {Colore.bianco, Colore.grigio, Colore.rosso, Colore.verde};
		return Arrays.asList(colori);
	}
	
	@Override
	protected boolean esisteSoluzione(Cella p) {
		if (griglia[griglia.length - 1][griglia[0].length - 1] != null) {
			numeroSoluzioni++;
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean ultimaSoluzione(Cella p) {
		return this.numeroSoluzioni == this.maxSoluzioni;
	}

	@Override
	protected void risolvi() {
		tentativo(puntiDiScelta(), new Cella(0, 0));
	}
	
	public static void main(String[] args) {
		Griglia g = new Griglia(10, 15, 5);
		g.risolvi();
		System.out.println("done");
	}

}
