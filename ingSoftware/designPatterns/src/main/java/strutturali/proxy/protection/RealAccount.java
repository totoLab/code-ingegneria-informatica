package strutturali.proxy.protection;

public class RealAccount implements BankAccount {

    private double balance;

    public RealAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void printBalance() {
        System.out.println("Balance is: " + balance);
    }
}
