package esercitazioni.gestioneaffittacamere2;

import java.util.ArrayList;
import java.util.Arrays;

import poo.anagrafe.Persona;
import poo.anagrafe.Persona.Sesso;
import poo.util.Data;

public class Applicazione {
	
	public static void main(String[] args) {
		
		// creazione hotel
		int numStanze = 5;
		ArrayList<Stanza> stanze = new ArrayList<Stanza>();
		for (int i = 1; i <= numStanze; i++) {
			stanze.add(new Stanza(4, 90.0, ""+i));
		}
			
		String nome = "Tazza"; int numStelle = 5; String via = "Argine Destro Calopinace, 69"; String citta = "RC";
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		Hotel h = new Hotel(stanze, prenotazioni, nome, numStelle, via, citta);
		//
		
		// prenotare
		ArrayList<Persona> famiglia = new ArrayList<>(
				Arrays.asList(
					new Persona("Antonio", "Labate", new Data(17, 10, 2002), Sesso.Maschile),
					new Persona("Mamma", "Labate", new Data(17, 10, 1980), Sesso.Femminile),
					new Persona("Papà", "Labate", new Data(17, 10, 1970), Sesso.Maschile)
				));
		
		Data d1 = new Data();
		Data d2 = new Data();
		d2.giornoDopo();
		h.aggiungiPrenotazione(famiglia, h.getStanze().get(2), d1, d2);
		//
		
		for (Stanza s : h.getStanze())
			System.out.println(s);
		
		for (Prenotazione p : h.getPrenotazioni())
			System.out.println(p);
		
		System.out.println(h.toString());
	}
	
}
