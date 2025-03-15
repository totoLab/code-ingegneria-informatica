package comportamentali.strategy;

public class RectangleCalculations implements CalculationStrategy {

    private double width;
    private double height;

    public RectangleCalculations(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}
