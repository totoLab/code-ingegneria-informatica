package comportamentali.strategy;

import java.awt.geom.Point2D;

public class Client {

    public static void main(String[] args) {

        Circle circle = new Circle(3, new Point2D.Double(2, 2));
        print(new CircleCalculations(circle.getRadius()));

        Rectangle rectangle = new Rectangle(new Point2D.Double(1, 3), 4, 2);
        print(new RectangleCalculations(rectangle.getWidth(), rectangle.getHeight()));
    }

    static void print(CalculationStrategy strategy) {
        System.out.printf("%s px,  %s px^2\n", strategy.calculatePerimeter(), strategy.calculateArea());
    }
}
