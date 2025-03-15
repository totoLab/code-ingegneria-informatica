package comportamentali.state.states;

public class CotturaInterrotta extends FornoState {


    public CotturaInterrotta(Forno forno) {
        super(forno);
        forno.setTubo(false);
        forno.setSecondsRemaining(0);
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
