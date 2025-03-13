package creazionali.builder;

import java.util.List;

public class StrangeListBuilder extends AbstractListBuilder {


    StrangeListBuilder(List<Integer> list) {
        super(list);
    }

    @Override
    public ListBuilder add(int i) {
        content.add(i * 2);
        return this;
    }

    @Override
    public ListBuilder filter(int i) {
        content.stream().filter(e -> e == i).findFirst().filter(e -> content.remove(e));
        return this;
    }

    @Override
    public ListBuilder sort() {
        content.sort((a, b) -> a * 2 - b / 2 + 5);
        return this;
    }
}
