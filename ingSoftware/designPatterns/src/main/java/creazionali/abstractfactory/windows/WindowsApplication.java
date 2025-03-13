package creazionali.abstractfactory.windows;

import creazionali.abstractfactory.Application;

public class WindowsApplication implements Application {

    @Override
    public void install() {
        System.out.println("App installed in C:\\Windows\\System32");
    }

    @Override
    public void runApplication() {
        System.out.println("App running on desktop");
    }
}
