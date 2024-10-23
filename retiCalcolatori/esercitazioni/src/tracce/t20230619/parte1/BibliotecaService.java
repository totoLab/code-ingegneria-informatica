package tracce.t20230619.parte1;

import java.util.*;

public class BibliotecaService {

    HashMap<String, Biblioteca> biblioteche = new HashMap<>();
    HashMap<Biblioteca, List<Prestito>> prestiti = new HashMap<>();

    class Biblioteca {
        String codice;
        String provincia;
        InfoBiblioteca infoBiblioteca;
    }

    class InfoBiblioteca {
        String codiceBiblioteca;
        String isbn;
        int numeroPrestiti;
    }

    class Prestito {
        String codiceBiblioteca, isbn, codiceFiscale;

        Prestito(String codiceBiblioteca, String isbn, String codiceFiscale) {
            this.codiceBiblioteca = codiceBiblioteca;
            this.isbn = isbn;
            this.codiceFiscale = codiceFiscale;
        }
    }

    private Biblioteca getBibliotecaFromCode(String codice) {
        if (biblioteche.containsKey(codice)) {
            return biblioteche.get(codice);
        }
        return null;
    }

    void prestito(String codiceBiblioteca, String isbn, String codiceFiscale) {
        Biblioteca biblioteca = getBibliotecaFromCode(codiceBiblioteca);
        if (biblioteca != null) {
            prestiti.put(biblioteca, new ArrayList<>());
        } else {
            // ...
        }
        List<Prestito> listaPrestiti = prestiti.get(biblioteca);
        listaPrestiti.add(new Prestito(codiceBiblioteca, isbn, codiceFiscale));
        // ... handle infoBiblioteca

    }

}
