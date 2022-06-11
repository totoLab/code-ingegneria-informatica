import java.util.*;

import terminale.Terminale;

import listaconcatenata.*;
import traccia16092020.*;
import traccia18022019.*;
import traccia20072021.*;

public class Test {

	public static void main(String[] args) {
		// testTraccia18022019();
		// testTraccia16092020();
		testTraccia20072021();
	}
	
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
		
		//for (Cliente c : listaClienti) { Terminale.stampa(c.getNome()); };
		
		/* for (Articolo a : listaArticoli) {
			Terminale.stampa("-------- articolo" + a + "----------");
			for (Cliente acquirente : a.getListaAcquirenti())
				Terminale.stampa(acquirente.getNome() + ": " + acquirente.getCitta());
		}
		*/
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
		Terminale.stampa(lista.toString());
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
}
