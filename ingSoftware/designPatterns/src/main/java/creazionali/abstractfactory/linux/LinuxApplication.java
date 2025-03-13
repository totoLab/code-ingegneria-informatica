package creazionali.abstractfactory.linux;

import creazionali.abstractfactory.Application;

public class LinuxApplication implements Application {

    @Override
    public void install() {
        System.out.println("Installed in /opt/app");
    }

    @Override
    public void runApplication() {
        System.out.println("Running inside terminal");
    }
}
