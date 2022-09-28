import java.util.*;
import terminale.*;
public class Test {

	public static void main(String[] args) {
		LinkedList<Tecnico> tecnici = new LinkedList<>();
		LinkedList<Intervento> interventi = new LinkedList<>();
		
		Tecnico t1 = new Tecnico("Mario", 20);
		Tecnico t2 = new Tecnico("Giovanni", 30);
		Tecnico t3 = new Tecnico("Luca", 15);
		
		tecnici.add(t1);
		tecnici.add(t2);
		tecnici.add(t3);		
		
		// for (Tecnico t : tecnici) Terminale.stampa(t);
		
		LinkedList<String> l1 = new LinkedList<>();
		l1.add("Riparazione");
		l1.add("Manutenzione");
		Intervento i1 = new Intervento("Giovanni", l1, 10, 2);
		
		LinkedList<String> l2 = new LinkedList<>();
		l2.add("Riparazione");
		Intervento i2 = new Intervento("Luca", l2, 15, 2);
		
		LinkedList<String> l3 = new LinkedList<>();
		l3.add("Manutenzione");
		Intervento i3 = new Intervento("Luca", l3, 15, 1);
		
		LinkedList<String> l4 = new LinkedList<>();
		l4.add("Riparazione");
		Intervento i4 = new Intervento("Mario", l4, 20, 2);
		
		LinkedList<String> l5 = new LinkedList<>();
		l5.add("Riparazione");
		Intervento i5 = new Intervento("Mario", l5, 10, 2);
		
		LinkedList<String> l6 = new LinkedList<>();
		l6.add("Manutenzione");
		Intervento i6 = new Intervento("Giovanni", l6, 15, 1);
		
		interventi.add(i1);
		interventi.add(i2);
		interventi.add(i3);
		interventi.add(i4);
		interventi.add(i5);
		interventi.add(i6);
		
		// for (Intervento i : interventi) Terminale.stampa(i);
		
		Sistema s = new Sistema(tecnici, interventi);
		Terminale.stampa(s.tecnicoMax());
	}

}
