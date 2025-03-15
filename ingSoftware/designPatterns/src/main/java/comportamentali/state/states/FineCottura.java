package comportamentali.state.states;

public class FineCottura extends FornoState {

    public FineCottura(Forno forno) {
        super(forno);
        forno.beep();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void apri() {
        forno.changeState(new PortaAperta(forno));
    }

    @Override
    public void chiudi() {

    }
}
