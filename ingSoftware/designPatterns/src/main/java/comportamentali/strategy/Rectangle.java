package comportamentali.strategy;

import java.awt.geom.Point2D;

public class Rectangle {

    private Point2D.Double corner;
    private double height;
    private double width;

    public Rectangle(Point2D.Double center, double height, double width) {
        this.corner = center;
        this.height = height;
        this.width = width;
    }

    public Point2D.Double getCorner() {
        return corner;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
