package comportamentali.iterator;

public class Client {

    public static void main(String[] args) {
        Team reggina1914 = new Team();
        reggina1914.addPlayer(new Player("Giuseppe Loiacono", "Defender"));
        reggina1914.addPlayer(new Player("Jeremy Menez", "Forward"));
        reggina1914.addPlayer(new Player("Lorenzo Crisetig", "Midfielder"));
        reggina1914.addPlayer(new Player("Alessandro Micai", "Goalkeeper"));
        reggina1914.addPlayer(new Player("Gianluca Di Chiara", "Defender"));
        reggina1914.addPlayer(new Player("Jérémy Ménez", "Forward"));
        reggina1914.addPlayer(new Player("Rigoberto Rivas", "Midfielder"));
        reggina1914.addPlayer(new Player("Adriano Montalto", "Forward"));
        reggina1914.addPlayer(new Player("Niccolò Bianchi", "Midfielder"));
        reggina1914.addPlayer(new Player("Thiago Cionek", "Defender"));
        reggina1914.addPlayer(new Player("Daniele Liotti", "Defender"));

        Iterator it = reggina1914.createIterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            System.out.println(i + ". " + it.next());
        }

        System.out.println();

        it = reggina1914.createRoleIterator("Defender");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
