package comportamentali.chainofresponsibility;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int logLevel) {
        super(logLevel);
    }

    @Override
    protected void createLog(String message) {
        System.out.println("$ " + message);
    }
}
