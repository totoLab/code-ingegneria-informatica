package creazionali.abstractfactory.windows;

import creazionali.abstractfactory.AbstractFactory;
import creazionali.abstractfactory.Application;
import creazionali.abstractfactory.HelperProgram;

public class WindowsFactory implements AbstractFactory {

    @Override
    public Application createApplication() {
        return new WindowsApplication();
    }

    @Override
    public HelperProgram createHelperProgram() {
        return new WindowsHelperProgram();
    }
}
