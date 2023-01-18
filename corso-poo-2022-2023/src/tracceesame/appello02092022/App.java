package tracceesame.appello02092022;

import java.io.*;
import java.util.*;

enum Option {prenota, libera, liberaTutti, statisticheLido, salva, ripristina, esci}

public class App {
	
	static LidoBalneare lido = new LidoBalneareConcreto(10, 10);
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		App.app();
	}
	
	private static void app() throws IOException, ClassNotFoundException {
		boolean finito = false;
		while (!finito) {
			switch (menu(testoMenu(), Option.values())) {
				case prenota: {
					Posizione p = lido.prenotaUnOmbrelloneRandom();
					System.out.println("Prenotazione effettuata, " + p);
					break;
				}
				case libera: {
					Posizione p = inserisciPosizione();
					lido.liberaOmbrellone(p);
					System.out.println("Liberato ombrellone " + p);
					break;
				}
				case liberaTutti: {
					lido.liberaTuttigliOmbrelloni();
					System.out.println("Liberati tutti gli ombrelloni");
					break;
				}
				case statisticheLido: {
					String s = statistiche(lido.ombrelloniLiberi(), lido.ombrelloniOccupati());
					System.out.println(s);
					break;
				}
				case salva: {
					salva(lido);
					System.out.println("Salvataggio effettuato.");
					break;
				}
				case ripristina: {
					lido = ripristina(lido);
					System.out.println("Ripristino effettuato.");
					break;
				}
				case esci: {
					finito = true;
				}
			}
		}
		System.out.println("Program exited successfully.");
	}
	
//  Facendo riferimento all’ADT LidoBalneare dell’esercizio precedente,
//	implementare un main interattivo con un menù testuale che permetta di gestire le prenotazioni di un ipotetico lido.
//	Il main deve altresì permettere il salvataggio e il recupero su file delle informazioni del LidoBalneare.
//	Per far questo si può utilizzare indistintamente un approccio basato sulla serializzazione
//	e gli ObjectInputStream e ObjectOutputStream di Java, oppure sulla gestione di file di tipo testo.
	
	private static String statistiche(Posizione[] ombrelloniLiberi, Posizione[] ombrelloniOccupati) {
		StringBuilder sb = new StringBuilder();
		sb.append("Statistiche lido:\n");
		sb.append("\tOmbrelloni liberi: ");
		sb.append(ombrelloniLiberi.length);
		sb.append("\n");
		sb.append("\tOmbrelloni occupati: ");
		sb.append(ombrelloniOccupati.length);
		return sb.toString();
	}

	@SuppressWarnings("resource")
	private static Posizione inserisciPosizione() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci fila ombrellone: ");
		int fila = sc.nextInt();
		System.out.println("Inserisci numero ombrellone nella fila: ");
		int ombrellone = sc.nextInt();
		return new Posizione(fila, ombrellone);
	}

	private static Option menu(String testoMenu, Option[] opzioni) {
		System.out.println(testoMenu);

		Scanner sc = new Scanner(System.in);
		int n;
		do {
			System.out.println("Inserire un indice valido scegliendo dalle opzioni presentate: ");
			n = sc.nextInt();
		} while (n < 0 || n >= opzioni.length);
		
		return opzioni[n];
	}
	
	
//  Il main deve altresì permettere il salvataggio e il recupero su file delle informazioni del LidoBalneare.
	private static void salva(LidoBalneare l) throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("miao.dat")));
		os.writeObject(l);
		System.out.println("Lido salvato su file.");
	}
	
	private static LidoBalneare ripristina(LidoBalneare l) throws IOException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(new File("miao.dat")));
		LidoBalneare l1 = null;
		try {
			l1 = (LidoBalneare) is.readObject();
		} catch (Exception e) {
			System.out.println("mannaia");
			System.exit(0);
		}
		return l1;
	}
	
	private static String testoMenu() {
		Option[] opzioni = Option.values();
		StringBuilder sb = new StringBuilder();
		sb.append("Menù di scelta:\n" );
		for (int i = 0; i < opzioni.length; i++) {
			sb.append(i);
			sb.append(" ");
			sb.append(opzioni[i]);
			sb.append("\n");
		}
		return sb.toString();
	}
}
