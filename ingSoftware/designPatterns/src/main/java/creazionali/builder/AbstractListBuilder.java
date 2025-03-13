package creazionali.builder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListBuilder implements ListBuilder {

    protected List<Integer> content;

    AbstractListBuilder(List<Integer> list) {
        content = new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
