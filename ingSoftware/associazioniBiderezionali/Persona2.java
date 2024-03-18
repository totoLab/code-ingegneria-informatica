import java.util.*;

class Automobile {
    // ...
    private Persona proprietario;
        // ...
    Persona getProprietario() {
        return proprietario;
    }

    void setProprietario(Persona p) {
        proprietario = p;
    }
        // ...
    }

class Persona {
    // ...
    private List<Automobile> auto;
        // ...
    public Collection<Automobile> getAutomobili() {
        return Collections.unmodifiableCollection(auto);
    }

    private void rimuoviAutomobile(Automobile a) {
        auto.remove(a);
        if (a.getProprietario() != null) a.setProprietario(null);
    }

    private void aggiungiAutomobile(Automobile a) {
        auto.add(a);
        if (a.getProprietario() != null) a.setProprietario(this);
    }
}