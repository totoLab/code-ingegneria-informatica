package creazionali.factorymethod;

public class CircleFactory implements ShapeFactory {

    @Override
    public Shape create() {
        return new Circle();
    }
}
