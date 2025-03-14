package comportamentali.chainofresponsibility;

public class TelegramLogger extends AbstractLogger {

    public TelegramLogger(int logLevel) {
        super(logLevel);
    }

    @Override
    protected void createLog(String message) {
        System.out.println("Telegram: " + message);
    }
}
