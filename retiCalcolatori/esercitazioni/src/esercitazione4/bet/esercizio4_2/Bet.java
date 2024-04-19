package esercitazione4.bet.esercizio4_2;

import java.net.InetAddress;

public class Bet {

    private InetAddress better;
    private int horse;
    private double amount;
    private int race;

    // race horse amount
    public Bet(InetAddress better, String bet) {
        String[] parts = bet.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException();
        }
        int race = Integer.parseInt(parts[0]);
        int horse = Integer.parseInt(parts[1]);
        double amount = Double.parseDouble(parts[2]);

        this.better = better;
        this.horse = horse;
        this.amount = amount;
        this.race = race;
    }

    public InetAddress getBetter() {
        return better;
    }

    public int getHorse() {
        return horse;
    }

    public double getAmount() {
        return amount;
    }

    public int getRace() {
        return race;
    }

    public static String usage() {
        return "Can't place bet, usage: <race> <horse> <amount>";
    }

}
