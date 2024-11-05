package tracce.supermercato;

public abstract class Cassa {

    abstract void svuotaCarrello(int N);

    abstract void scansiona();

    abstract void paga(int N);

    abstract void prossimoCliente();

}
