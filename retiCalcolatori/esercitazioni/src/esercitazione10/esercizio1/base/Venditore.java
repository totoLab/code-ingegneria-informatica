package esercitazione10.esercizio1.base;

import utils.Logging;

import java.io.IOException;
import java.net.InetAddress;

import static utils.Logging.print;

public class Venditore {
    InetAddress addr;

    Venditore() {
        try {
            addr = InetAddress.getLocalHost();
        } catch (IOException e) {
            print(Logging.Type.ERROR, "Error in retrieving address", null, null, e);
        }
    }

    InetAddress getInetAddress() {
        return addr;
    }

}
