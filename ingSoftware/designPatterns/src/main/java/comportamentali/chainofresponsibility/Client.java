package comportamentali.chainofresponsibility;

public class Client {

    public static void main(String[] args) {
        Logger console = new ConsoleLogger(2);
        Logger telegram = new TelegramLogger(1);

        console.setNextHandler(telegram);

        console.log("test", 3);

    }
}
