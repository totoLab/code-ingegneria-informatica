package creazionali.abstractfactory.linux;

import creazionali.abstractfactory.HelperProgram;

public class LinuxHelperProgram implements HelperProgram {


    @Override
    public String getShortcut() {
        return "ctrl+f1";
    }

    @Override
    public String getDescription() {
        return "Man Pages GUI";
    }
}
