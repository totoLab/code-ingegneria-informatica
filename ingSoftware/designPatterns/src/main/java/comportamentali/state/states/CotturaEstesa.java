package comportamentali.state.states;

public class CotturaEstesa extends FornoState {

    public CotturaEstesa(Forno forno) {
        super(forno);
        forno.addSecondsRemaining(60);
    }

    @Override
    public void start() {
        forno.changeState(new CotturaEstesa(forno));
    }

    @Override
    public void stop() {

    }

    @Override
    public void apri() {
        forno.changeState(new CotturaInterrotta(forno));
    }

    @Override
    public void chiudi() {

    }
}
