package comportamentali.state.states;

public abstract class FornoState {

    protected Forno forno;

    public FornoState(Forno forno) {
        this.forno = forno;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void apri();

    public abstract void chiudi();
}
