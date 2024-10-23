package es5.barbiere;

public class Cliente extends Thread {

    private Sala s;
    private int id;

    Cliente(Sala s, int id) {
        this.s = s;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Cliente %d è arrivato.%n", id);
            boolean taglioEffettuato = s.attendiTaglio();
            if (taglioEffettuato) {
                System.out.printf("Cliente %d ha tagliato i capelli.%n", id);
            } else {
                System.out.printf("Cliente %d va via.%n", id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
