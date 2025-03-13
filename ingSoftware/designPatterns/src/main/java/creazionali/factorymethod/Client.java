package creazionali.factorymethod;

public class Client {

    public static void main(String[] args) {
        ShapeFactory factory = new CircleFactory();
        factory.createThree();

        factory = new TriangleFactory();
        factory.createThree();
    }
}
