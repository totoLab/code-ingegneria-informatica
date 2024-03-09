class Automobile {

    private Persona proprietario;

    public Persona getProprietario() {
        return proprietario;
    }

    public void setProprietario(Persona p) {
        p.setProprietario(this)
        proprietario = p;
    }
}

class Persona {

    private List<Automobile> auto;

    public Collection<Automobile> getAutomobili() {
        return Collections.unmodifiableCollection(auto);
    }

    void setProprietario(Automobile a) {
        Persona old = a.getProprietario();
        if (old != null) old.rimuoviAutomobile(a);
        this.aggiungiAutomobile(a);
    }

    void rimuoviAutomobile(Automobile a) {
        auto.remove(a);
    }

    void aggiungiAutomobile(Automobile a) {
        auto.add(a);
    }
    
}