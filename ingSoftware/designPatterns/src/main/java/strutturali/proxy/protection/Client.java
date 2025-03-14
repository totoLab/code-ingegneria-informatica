package strutturali.proxy.protection;

public class Client {

    public static void main(String[] args) {
        ProxyAccount account = new ProxyAccount("admin", "ciaooo");
        account.printBalance();

        account.authenticate("admin", "admin1");
        account.authenticate("admin", "admin2");
        account.authenticate("admin", "admin");
        account.printBalance();

        account.authenticate("admin", "admin");
        account.printBalance();
    }
}
