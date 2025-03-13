package creazionali.factorymethod;

public class TriangleFactory implements ShapeFactory {

    @Override
    public Shape create() {
        return new Triangle();
    }
}
