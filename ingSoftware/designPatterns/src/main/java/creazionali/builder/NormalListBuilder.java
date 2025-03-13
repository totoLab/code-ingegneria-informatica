package creazionali.builder;

import java.util.List;

public class NormalListBuilder extends AbstractListBuilder {

    NormalListBuilder(List<Integer> list) {
        super(list);
    }

    @Override
    public ListBuilder add(int i) {
        content.add(i);
        return this;
    }

    @Override
    public ListBuilder filter(int i) {
        content.stream().filter(e -> e == i).findFirst().ifPresent(e -> content.remove(e));
        return this;
    }

    @Override
    public ListBuilder sort() {
        content.sort((e1, e2) -> e2 - e1);
        return this;
    }
}
