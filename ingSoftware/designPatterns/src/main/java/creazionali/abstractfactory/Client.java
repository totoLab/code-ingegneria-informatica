package creazionali.abstractfactory;

import creazionali.abstractfactory.linux.LinuxFactory;
import creazionali.abstractfactory.windows.WindowsFactory;

public class Client {

    public static void main(String[] args) {
        AbstractFactory factory = new LinuxFactory();
        getHelpAndRun(factory);
        factory = new WindowsFactory();
        getHelpAndRun(factory);

    }

    private static void getHelpAndRun(AbstractFactory factory) {
        System.out.println("Trying to install and run application...");
        HelperProgram helper = factory.createHelperProgram();
        System.out.println("If you need help press: " + helper.getShortcut() + " to call " + helper.getDescription());

        Application app = factory.createApplication();
        app.install();
        app.runApplication();
    }
}
