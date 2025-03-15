package comportamentali.state.states;

public class Forno {

    private boolean luce;
    private boolean tubo;
    private int secondsRemaining;

    private FornoState state;

    public Forno() {
        luce = false;
        tubo = false;
        secondsRemaining = 0;
        changeState(new PortaChiusa(this));
    }

    void setLuce(boolean luce) {
        this.luce = luce;
    }

    void setTubo(boolean tubo) {
        this.tubo = tubo;
    }

    void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }

    void addSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = this.secondsRemaining + secondsRemaining;
    }

    public boolean getLuce() {
        return luce;
    }

    public boolean getTubo() {
        return tubo;
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
    }

    void changeState(FornoState state) {
        this.state = state;
    }

    public void start() {
        state.start();
    }

    public void stop() {
        state.stop();
    }

    public void apri() {
        state.apri();
    }

    public void chiudi() {
        state.chiudi();
    }

    void beep() {
        System.out.println("beep");
    }
}