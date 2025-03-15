package comportamentali.state.states;

public class PortaAperta extends FornoState {

    public PortaAperta(Forno forno) {
        super(forno);
        forno.setLuce(true);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void apri() {

    }

    @Override
    public void chiudi() {
        forno.changeState(new PortaChiusa(forno));
    }
}
