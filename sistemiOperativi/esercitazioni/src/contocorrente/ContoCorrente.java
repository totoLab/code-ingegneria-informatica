package contocorrente;

import java.util.Random;

public abstract class ContoCorrente {

    protected int saldo;

    protected ContoCorrente() {
        this(0);
    }

    protected ContoCorrente(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    abstract public void deposita(int importo);

    abstract public void preleva(int importo);

    public static void main(String[] args) {

        int D = 100000;
        int N = 5000;
        ContoCorrente cc = new ContoCorrenteAtomic(D);
        // ContoCorrente cc = new ContoCorrenteNTS(D);
        int saldoIniziale = cc.getSaldo();

        int n = 200;
        Correntista[] correntisti = new Correntista[n];
        Random rand = new Random();
        for (int i = 0; i < correntisti.length; i++) {
            int X = 100;  //rand.nextInt(10, 50);
            correntisti[i] = new Correntista(cc, N, X);
        }

        for (int i = 0; i < correntisti.length; i++) {
            correntisti[i].start();
        }

        try {
            for (int i = 0; i < correntisti.length; i++) {
                correntisti[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (cc.getSaldo() == saldoIniziale) {
            System.out.println("Corretto");
        } else {
            System.out.println("Errore");
        }

    }
}
