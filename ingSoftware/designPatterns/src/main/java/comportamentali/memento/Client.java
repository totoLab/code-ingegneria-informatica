package comportamentali.memento;

import java.util.Random;

public class Client {

    public static void main(String[] args) {
        Database database = new Database("people");

        newTransaction(database, new String[]{"john", "doe", "johndoe@gmail.com"});
        newTransaction(database, new String[]{"Cohen", "Oneal", "CohenOneal@gmail.com"});

        System.out.println("\n" + database);
    }

    static void newTransaction(Database db, String[] row) {
        MementoNarrow temp = db.save();
        System.out.println("Transaction started");

        Random random = new Random();
        if (random.nextInt(0, 10) < 2) {
            System.out.println("Transaction failed, restoring automatically");
            db.restore(temp);
        } else {
            db.add(row);
            System.out.println("Transaction finished");
        }
    }
}
