import java.util.*;

class Automobile {
    // ...
private Persona proprietario;
    // ...
public Persona getProprietario() {
    return proprietario;
}

public void setProprietario(Persona p) {
    if (proprietario != null) proprietario.rimuoviAutomobile(this);
    proprietario = p;
    if (proprietario != null) proprietario.aggiungiAutomobile(this);
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

void rimuoviAutomobile(Automobile a) {
    auto.remove(a);
} // visibile solo a Automobile

void aggiungiAutomobile(Automobile a) {
    auto.add(a);
} // visibile solo a Automobile
    // ...
}