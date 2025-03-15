package comportamentali.strategy;

public class CircleCalculations implements CalculationStrategy {

    private final double radius;

    public CircleCalculations(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
