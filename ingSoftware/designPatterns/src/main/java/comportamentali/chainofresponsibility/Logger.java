package comportamentali.chainofresponsibility;

public interface Logger {

    void log(String message, int level);
    void setNextHandler(Logger logger);
}
