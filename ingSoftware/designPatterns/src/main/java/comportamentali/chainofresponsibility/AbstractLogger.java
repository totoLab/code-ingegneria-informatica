package comportamentali.chainofresponsibility;

public abstract class AbstractLogger implements Logger {

    protected final int logLevel;
    protected Logger nextLogger;

    public AbstractLogger(int logLevel) {
        this.logLevel = logLevel;
    }

    protected abstract void createLog(String message);

    @Override
    public void log(String message, int level) {
        if (logLevel <= level) {
            createLog(message);
        } else if (nextLogger != null) {
            nextLogger.log(message, level);
        }
    }

    @Override
    public void setNextHandler(Logger logger) {
        this.nextLogger = logger;
    }
}
