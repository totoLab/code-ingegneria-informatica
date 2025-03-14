package strutturali.proxy.protection;

public class ProxyAccount implements BankAccount {

    private RealAccount realAccount;
    private boolean isAuthenticated;
    private int retries = 0;

    public ProxyAccount(String username, String password) {
        authenticate(username, password);
    }

    public void authenticate(String username, String password) {
        if (!isAuthenticated) {
            if (retries >= 3) {
                System.out.println("You are banned");
                return;
            }
            isAuthenticated = Database.getInstance().checkCredentials(username, password);
            if (!isAuthenticated) {
                retries++;
                System.out.println("Wrong credentials");
            } else {
                retries = 0;
                realAccount = new RealAccount(10000);
            }
        }
    }

    @Override
    public void printBalance() {
        if (isAuthenticated) {
            realAccount.printBalance();
        } else {
            System.out.println("Can't access balance");
        }
    }
}
