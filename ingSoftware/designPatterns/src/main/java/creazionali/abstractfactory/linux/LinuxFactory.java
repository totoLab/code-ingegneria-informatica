package creazionali.abstractfactory.linux;

import creazionali.abstractfactory.AbstractFactory;
import creazionali.abstractfactory.Application;
import creazionali.abstractfactory.HelperProgram;

public class LinuxFactory implements AbstractFactory {

    @Override
    public Application createApplication() {
        return new LinuxApplication();
    }

    @Override
    public HelperProgram createHelperProgram() {
        return new LinuxHelperProgram();
    }
}
