package comportamentali.state.states;

public class InizioCottura extends FornoState {

    public InizioCottura(Forno forno) {
        super(forno);
        forno.setLuce(true);
        forno.setTubo(true);
        forno.setSecondsRemaining(60);
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
