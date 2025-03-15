package comportamentali.strategy;

import java.awt.geom.Point2D;

public class Circle {

    private double radius;
    public Point2D.Double center;

    public Circle(double radius, Point2D.Double center) {
        this.radius = radius;
        this.center = center;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
}
