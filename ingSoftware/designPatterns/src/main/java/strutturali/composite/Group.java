package strutturali.composite;

import java.util.ArrayList;
import java.util.List;

public class Group extends AbstractGraphicObject {

    List<AbstractGraphicObject> children;

    public Group() {
        children = new ArrayList<>();
    }

    public Group(List<AbstractGraphicObject> children) {
        this.children = children;
    }

    @Override
    void add(AbstractGraphicObject graphicObject) {
        children.add(graphicObject);
    }

    @Override
    void remove(AbstractGraphicObject graphicObject) {
        children.remove(graphicObject);
    }

    @Override
    void print() {
        System.out.print("[");
        for (AbstractGraphicObject child : children) {
            child.print();
            System.out.print(" ");
        }
        System.out.print("]");
    }
}
