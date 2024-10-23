package contocorrente;

public class ContoCorrenteNTS extends ContoCorrente {

    public ContoCorrenteNTS(int importo) {
        super(importo);
    }

    @Override
    public void deposita(int importo) {
        super.saldo += importo;
    }

    @Override
    public void preleva(int importo) {
        super.saldo -= importo;
    }
}

class Correntista extends Thread {

    private ContoCorrente cc;
    private int n_operations;
    private int importo;

    Correntista(ContoCorrente cc, int n_operations, int importo) {
        this.cc = cc;
        if (n_operations % 2 != 0) throw new RuntimeException("Numero pari di operazioni!");
        this.n_operations = n_operations;
        this.importo = importo;
    }

    @Override
    public void run() {
        for (int i = 0; i < n_operations; i++) {
            if (i % 2 == 0) {
                cc.deposita(importo);
            } else {
                cc.preleva(importo);
            }
        }
    }
}
