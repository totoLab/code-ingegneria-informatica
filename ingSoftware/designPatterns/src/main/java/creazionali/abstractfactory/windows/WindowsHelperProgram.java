package creazionali.abstractfactory.windows;

import creazionali.abstractfactory.HelperProgram;

public class WindowsHelperProgram implements HelperProgram {

    @Override
    public String getShortcut() {
        return "ctrl+r";
    }

    @Override
    public String getDescription() {
        return "New clippy implementation";
    }
}
