package tracceesame.appello11072022;

import java.util.*;
import poo.ricorsione.*;
import tracceesame.appello11072022.PezzoDegliScacchi.*;

public class NTorri extends Backtracking<Integer, Integer> {

	private Scacchiera scacchiera;
	private int n, nrSol = 0;
	
	public NTorri(int n) {
		if (n < 0) throw new IllegalArgumentException();
		this.n = n;
		this.scacchiera = new ScacchieraSuLinkedList(n);
	}

	@Override
	protected boolean assegnabile(Integer r, Integer c) {
		return !scacchiera.ciSonoPezziSullaRigaEColonna(r, c);
	}

	@Override
	protected void assegna(Integer r, Integer c) {
		scacchiera.posiziona(new PezzoDegliScacchi(Tipo.torre, Colore.bianco, r, c));
	}

	@Override
	protected void deassegna(Integer r, Integer c) {
		scacchiera.rimuovi(r, c);
	}

	@Override
	protected void scriviSoluzione(Integer p) {
		nrSol++;
		System.out.print(nrSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j )
				if( scacchiera.contenuto(i, j) != null ) {
					System.out.print("<"+i+","+j+">");
					break; //interrompe il ciclo di for interno
				}
		}
		System.out.println();
	}

	@Override
	protected List<Integer> puntiDiScelta() {
		ArrayList<Integer> ps=new ArrayList<>();
		for( int i=0; i<n; ++i ) ps.add(i);
		return ps;
	}

	@Override
	protected Collection<Integer> scelte(Integer p) {
		List<Integer> s=new ArrayList<>();
		for( int i=0; i<n; ++i ) s.add(i);
		return s;
	}

	protected boolean esisteSoluzione( Integer p ) {
		return p == scacchiera.dimensione() - 1;
	}
	
	@Override
	protected void risolvi() {
		tentativo( puntiDiScelta(), 0 );
	}
	
	public static void main( String[] args ) {
		int ordineMax = 5;
		for (int i = 0; i < ordineMax; i++) {
			System.out.println("Test scacchiera ordine " + i);
			new NTorri(i).risolvi();
		}
		System.out.println("done");
	}//main

}
