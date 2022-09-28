import java.lang.reflect.Array;
import java.util.*;

public class Sistema {
	
	private ArrayList<Utente> utenti;
	private ArrayList<Tweet> tweet;

	public Sistema(ArrayList<Utente> utenti, ArrayList<Tweet> tweet) {
		this.utenti = utenti;
		this.tweet = tweet;
	}

	public ArrayList<String> cittaDiversa() {
		ArrayList<String> ret = new ArrayList<>();
		for (Utente u : utenti)
			if (tuttiTweetCittaDiversa(u))
				ret.add(u.getCF());
		return ret;
	}

	private boolean tuttiTweetCittaDiversa(Utente u) {
		for (Tweet t : tweet)
			if (t.getCFutente().equals(t) && t.getCittaEmissione().equals(u.getCitta))
				return false;
		return true;
	}

	public ArrayList<String> listaUtenti() {
		ArrayList<String> ret = new ArrayList<>();

		for (Utente u : utenti)
			if (almeno2TweetTagDiversi())
				ret.add(u.getCF());
		return ret;
	}

	private boolean almeno2TweetTagDiversi() {
		ArrayList<Tweet> tweetUtente = new ArrayList<>();
		for (Tweet t : tweet) {
			if (t.getCFutente().equals(u.getCF())) {
				tweetUtente.add(t);
			}
		}
		for (int i = 0; i < tweetUtente.size(); i++)
			for (int j = i + 1; j < tweetUtente.size(); j++) {
				ArrayList<String> tagi = tweetUtente.get(i).getTag();
				ArrayList<String> tagj = tweetUtente.get(j).getTag();
				tagi.retainAll(tagj);
				if (tagi.isEmpty()) {
					return true;
				}
			}
		return false;
	}

	public String tagOfTheDay(int data) {
		ArrayList<String> tagInteresse = tagGiorno(data);
		String tagMax = tagInteresse.get(0);
		int occorrenzeMax = Collections.frequency(tagInteresse, tagMax);
		for (int i = 1; i < tagInteresse.size(); i++) {
			String tagi = tagInteresse.get(i);
			int occ = Collections.frequency(tagInteresse, tagi);
			if (occ > occorrenzeMax) {
				tagMax = tagi;
				occorrenzeMax = occ;
			}
		}
		return tagMax;
	}
	
	private ArrayList<String> tagGiorno(int data) {
		ArrayList<String> ret = new ArrayList<>();
		for (Tweet t : tweet) {
			if (t.getDataEmissione() == data) {
				ArrayList<String> tags = t.getTag();
				for (String tag : tags) {
					ret.add(tag);
				}
			}
		}
		return ret;
	}
}