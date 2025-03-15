package comportamentali.state.states;

public class PortaChiusa extends FornoState {

    public PortaChiusa(Forno forno) {
        super(forno);
        forno.setTubo(false);
    }

    @Override
    public void start() {
        forno.changeState(new InizioCottura(forno));
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
