package comportamentali.mediator;

public class Client {

    public static void main(String[] args) {

        Mediator controlTower = new ControlTower(5);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Airplane(controlTower);
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }
}
