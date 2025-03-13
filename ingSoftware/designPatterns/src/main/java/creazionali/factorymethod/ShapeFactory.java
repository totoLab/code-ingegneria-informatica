package creazionali.factorymethod;

public interface ShapeFactory {

    Shape create();

    default void createThree() {
        for (int i = 0; i < 3; i++) {
            Shape shape = create();
            System.out.println(shape.getName());
        }
    }
}
