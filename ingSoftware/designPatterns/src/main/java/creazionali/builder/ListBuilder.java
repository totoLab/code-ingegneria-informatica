package creazionali.builder;

public interface ListBuilder {

    ListBuilder add(int i);

    ListBuilder filter(int i);

    ListBuilder sort();
}
