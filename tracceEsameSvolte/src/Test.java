import java.util.*;

import terminale.Terminale;

import listaconcatenata.*;
import traccia16092020.*;
//import traccia18022019.*;
import traccia20072021.*;
import traccia23072020.*;
import traccia03072020.*;
import traccia20092021.*;
import traccia23062021.*;
import traccia17022021.*;
import traccia27012021.*;

public class Test {

	public static void main(String[] args) {
		// testTraccia18022019();
		// testTraccia16092020();
		// testTraccia20072021();
		// testTraccia23072020();
		// testTraccia03072020();
		// testTraccia20092021();
		// testTraccia23062021();
		// testTraccia17022021();
		testTraccia27012021();
	}
	/*
	public static void testTraccia18022019() {
		Cliente c1 = new Cliente("Rossi","Cosenza");
		Cliente c2 = new Cliente("Bianchi","Torino");
		Cliente c3 = new Cliente("Verdi","Cosenza");
		Cliente c4 = new Cliente("Neri","Roma");
		Cliente c5 = new Cliente("Marroni","Milano");
		Cliente c6 = new Cliente("Gialli","Milano");
		
		ArrayList<Cliente> listaClienti = new ArrayList<>();
		listaClienti.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		
		Articolo a1 = new Articolo("art1", new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c6)), 1);
		Articolo a2 = new Articolo("art2", new ArrayList<>(Arrays.asList(c1, c3)), 2);
		Articolo a3 = new Articolo("art3", new ArrayList<>(Arrays.asList(c2, c3, c4)), 2);
		Articolo a4 = new Articolo("art4", new ArrayList<>(Arrays.asList(c5)), 3);
		Articolo a5 = new Articolo("art5", new ArrayList<>(Arrays.asList(c2, c4, c5)), 3);
		Articolo a6 = new Articolo("art6", new ArrayList<>(Arrays.asList(c1, c4)), 4);
		Articolo a7 = new Articolo("art7", new ArrayList<>(Arrays.asList(c3, c5, c6)), 4);
		Articolo a8 = new Articolo("art8", new ArrayList<>(Arrays.asList(c6)), 5);
		
		ArrayList<Articolo> listaArticoli = new ArrayList<>();
		listaArticoli.addAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8));
		
		traccia18022019.Sistema s = new traccia18022019.Sistema(listaClienti, listaArticoli);
		Terminale.stampa(s.articoliCitta("Cosenza"));
		Terminale.stampa(s.acquirentiUnici(2,3));
		Terminale.stampa(s.acquirentiComuni(c3, c6));
	}
	
	public static void testTraccia16092020() {
		Utente u1 = new Utente("Utente A", "Roma");
		Utente u2 = new Utente("Utente B", "Roma");
		Utente u3 = new Utente("Utente C", "Milano");
		Utente u4 = new Utente("Utente D", "Milano");

		Messaggio m1 = new Messaggio("Utente A","Utente B", 10, false);
		Messaggio m2 = new Messaggio("Utente A","Utente B", 11, false);
		Messaggio m3 = new Messaggio("Utente B","Utente C", 15, true);
		Messaggio m4 = new Messaggio("Utente B","Utente D", 16, false);
		Messaggio m5 = new Messaggio("Utente C","Utente A", 15, true);
		Messaggio m6 = new Messaggio("Utente C","Utente D", 16, false);
		Messaggio m7 = new Messaggio("Utente D","Utente C", 16, true);
		
		ArrayList<Utente> listaUtenti = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
		ArrayList<Messaggio> listaMessaggi = new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7));
		
		traccia16092020.Sistema s = new traccia16092020.Sistema(listaUtenti, listaMessaggi);
		
		Terminale.stampa(s.nessunaLettura("Utente B"));
		Terminale.stampa(s.cittaUnica(14, 16));
		Terminale.stampa(s.mittentiFrequenti(15, "Roma"));
		
		// ------------------------------------------- //
		
		ArrayList<Integer> listaDaCopiare = new ArrayList<>(Arrays.asList(7,2,7,2,7,2,7,2));
		ListaConcatenataInt lista = new ListaConcatenataInt(listaDaCopiare);
		// Terminale.stampa(lista.toString());
		Terminale.stampa(lista.stesseSottoSequenze());
	}
	
	public static void testTraccia20072021() {
		Contratto c1 = new Contratto("Juventus", "Bianchi", 10000, new LinkedList<Integer>(Arrays.asList(300,200,300)));
		Contratto c2 = new Contratto("Inter", "Neri", 5000, new LinkedList<Integer>(Arrays.asList(100,50,100)));
		Contratto c3 = new Contratto("Juventus", "Verdi", 10000, new LinkedList<Integer>(Arrays.asList(300,200,300)));
		Contratto c4 = new Contratto("Juventus", "Rossi", 5000, new LinkedList<Integer>(Arrays.asList(100,100)));
		Contratto c5 = new Contratto("Napoli", "Rossetti", 20000, new LinkedList<Integer>(Arrays.asList(100,200,200,200)));
		LinkedList<Contratto> listaContratti = new LinkedList<>(Arrays.asList(c1, c2, c3, c4, c5));
		
		Calciatore p1 = new Calciatore("Bianchi", "Inter");
		Calciatore p2 = new Calciatore("Verdi", "Napoli");
		Calciatore p3 = new Calciatore("Rossi", "Roma");
		Calciatore p4 = new Calciatore("Rossetti", "Inter");
		Calciatore p5 = new Calciatore("Neri", "Napoli");
		LinkedList<Calciatore> listaCalciatori = new LinkedList<>(Arrays.asList(p1, p2, p3, p4, p5));
		
		traccia20072021.Sistema s = new traccia20072021.Sistema(listaContratti, listaCalciatori);
		
		Terminale.stampa(s.verificaDati());
		Terminale.stampa(s.squadreAttive(2000));
		Terminale.stampa(s.calciatoriPocoPremiati(100));
		
		// ---------------------------------------------- //
		
		ArrayList<Integer> listaDaCopiare = new ArrayList<>(Arrays.asList(7,7,1,1,-1,3));
		ListaConcatenataInt lista = new ListaConcatenataInt(listaDaCopiare);
		Terminale.stampa(lista.verifica0ePoiPositivi());
	}
	
	public static void testTraccia23072020() {
		Squadra s1 = new Squadra("Juventus", "Torino");
		Squadra s2 = new Squadra("Milan", "Milano");
		Squadra s3 = new Squadra("Lazio", "Roma");
		LinkedList<Squadra> listaSquadre = new LinkedList<>(Arrays.asList(s1, s2, s3));
		
		Partita p1 = new Partita("Juventus", "Milan", 2, 0, "Arbitro A", "Roma"); 
		Partita p2 = new Partita("Milan", "Juventus", 1,3, "Arbitro A", "Roma");
		Partita p3 = new Partita("Lazio", "Milan", 2, 0, "Arbitro A", "Roma");
		Partita p4 = new Partita("Juventus", "Lazio", 2, 0, "Arbitro B", "Milano");
		Partita p5 = new Partita("Lazio", "Juventus", 2, 1, "Arbitro B", "Milano");
		Partita p6 = new Partita("Milan", "Lazio", 1, 1, "Arbitro A", "Roma");
		LinkedList<Partita> listaPartite = new LinkedList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		traccia23072020.Torneo t = new traccia23072020.Torneo(listaSquadre, listaPartite);
		
		Terminale.stampa(t.squadreCasalinghe());
		Terminale.stampa(t.arbitriFuoriCItta());
		Terminale.stampa(t.arbitri3squadre());
	}
	
	public static void testTraccia03072020() {
		Negozio n1 = new Negozio("Negozio A", "Roma");
		Negozio n2 = new Negozio("Negozio B", "Roma");
		Negozio n3 = new Negozio("Negozio C", "Milano");
		ArrayList<Negozio> listaNegozi = new ArrayList<>(Arrays.asList(n1, n2, n3));
		
		Acquisto a1 = new Acquisto("Negozio A", 10, "ABCDEF", "Roma");
		Acquisto a2 = new Acquisto("Negozio A", 10, "GHIJKL", "Napoli");
		Acquisto a3 = new Acquisto("Negozio A", 10, "MNOPQR", "Palermo");
		Acquisto a4 = new Acquisto("Negozio B", 10, "MNOPQR", "Palermo");
		Acquisto a5 = new Acquisto("Negozio B", 20, "GHIJKL", "Napoli");
		Acquisto a6 = new Acquisto("Negozio C", 20, "MNOPQR", "Palermo");
		Acquisto a7 = new Acquisto("Negozio C", 20, "ABCDEF", "Roma");
		ArrayList<Acquisto> listaAcquisti = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7));
	
		traccia03072020.Sistema s = new traccia03072020.Sistema(listaNegozi, listaAcquisti);
		
		Terminale.stampa(s.negoziPreferiti(10));
		Terminale.stampa(s.clientiEsterniPeriodo(10, 20));
		Terminale.stampa(s.clientiCittaDiverse());
	}
	
	public static void testTraccia20092021() {
		Tennista t1 = new Tennista("Angela");
		Tennista t2 = new Tennista("Beatrice");
		Tennista t3 = new Tennista("Carla");
		Tennista t4 = new Tennista("Daria");
		Tennista t5 = new Tennista("Elisa");
		LinkedList<Tennista> listaTennisti = new LinkedList<>(Arrays.asList(t1, t2, t3, t4, t5));
		
		Incontro i1 = new Incontro(1, "Elisa", "Daria", "quarto di finale");
		Incontro i2 = new Incontro(1, "Elisa", "Beatrice", "semifinale");
		Incontro i3 = new Incontro(1, "Angela", "Carla", "semifinale");
		Incontro i4 = new Incontro(1, "Angela", "Elisa", "finale");
		Incontro i5 = new Incontro(2, "Carla", "Beatrice", "quarto di finale");
		Incontro i6 = new Incontro(2, "Carla", "Angela", "semifinale");
		LinkedList<Incontro> listaIncontri = new LinkedList<>(Arrays.asList(i1, i2, i3, i4, i5, i6));
		
		traccia20092021.Sistema s = new traccia20092021.Sistema(listaTennisti, listaIncontri);
		
		Terminale.stampa(s.edizioniOK("Elisa"));
		Terminale.stampa(s.tennistiFrequenti(2));
		Terminale.stampa(s.tennistiSorprendenti(1, 2));
	}
	
	public static void testTraccia23062021() {
		Operaio o1 = new Operaio("Alberto", 10);
		Operaio o2 = new Operaio("Gianni", 12);
		Operaio o3 = new Operaio("Eva", 12);
		Operaio o4 = new Operaio("Filippo", 8);
		LinkedList<Operaio> listaOperai = new LinkedList<>(Arrays.asList(o1, o2, o3, o4));
		
		Intervento i1 = new Intervento("I1", 1, 10, new LinkedList<String>(Arrays.asList("Alberto", "Filippo")));
		Intervento i2 = new Intervento("I2", 2, 9, new LinkedList<String>(Arrays.asList("Alberto", "Eva")));
		Intervento i3 = new Intervento("I3", 3, 8, new LinkedList<String>(Arrays.asList("Alberto", "Gianni", "Eva")));
		LinkedList<Intervento> listaInterventi = new LinkedList<>(Arrays.asList(i1, i2, i3));
		
		traccia23062021.Sistema s = new traccia23062021.Sistema(listaOperai, listaInterventi);
		
		Terminale.stampa(s.interventoPiuCostoso(1, 2));
		Terminale.stampa(s.operaiSemprePresenti(9));
		Terminale.stampa(s.operaiUtilizzatiConDurateDiverse());
	}
	*/
	public static void testTraccia17022021() {
		Componente c1 = new Componente("C1", 100);
		Componente c2 = new Componente("C2", 200);
		Componente c3 = new Componente("C3", 50);
		Componente c4 = new Componente("C4", 100);
		LinkedList<Componente> listaComponenti = new LinkedList<>(Arrays.asList(c1, c2, c3, c4));
		
		Articolo a1 = new Articolo("Articolo A", 210, new LinkedList<>(Arrays.asList("C1", "C4")));
		Articolo a2 = new Articolo("Articolo B", 400, new LinkedList<>(Arrays.asList("C1", "C2", "C3")));
		Articolo a3 = new Articolo("Articolo C", 370, new LinkedList<>(Arrays.asList("C1", "C2","C3")));
		LinkedList<Articolo> listaArticoli = new LinkedList<>(Arrays.asList(a1, a2, a3));
		
		traccia17022021.Sistema s = new traccia17022021.Sistema(listaComponenti, listaArticoli);
		
		Terminale.stampa(s.articoloTop());
		Terminale.stampa(s.componentiUniversali());
		Terminale.stampa(s.articoliComponentiCostosi(150));
	}
	
	public static void testTraccia27012021() {
		Fornitore f1 = new Fornitore("F1", "Roma");
		Fornitore f2 = new Fornitore("F2", "Roma");
		Fornitore f3 = new Fornitore("F3", "Milano");
		Fornitore f4 = new Fornitore("F4", "Roma");
		LinkedList<Fornitore> listaFornitori = new LinkedList<>(Arrays.asList(f1, f2, f3, f4));
		
		Merce m1 = new Merce("M1", "Barilla", new LinkedList<>(Arrays.asList("F1", "F4")));
		Merce m2 = new Merce("M2", "Barilla", new LinkedList<>(Arrays.asList("F1", "F2", "F3")));
		Merce m3 = new Merce("M3", "Voiello", new LinkedList<>(Arrays.asList( "F3")));
		LinkedList<Merce> listaMerci = new LinkedList<>(Arrays.asList(m1, m2, m3));
		
		traccia27012021.Sistema s = new traccia27012021.Sistema(listaFornitori, listaMerci);
		
		Terminale.stampa(s.grandiFornitori());
		Terminale.stampa(s.fornitoriMonoMarca("Barilla"));
		Terminale.stampa(s.merciCittaDiverse());
	}
}
