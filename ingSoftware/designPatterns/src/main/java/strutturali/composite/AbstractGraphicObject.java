package strutturali.composite;

public abstract class AbstractGraphicObject {

    abstract void add(AbstractGraphicObject graphicObject);

    abstract void remove(AbstractGraphicObject graphicObject);

    abstract void print();
}
