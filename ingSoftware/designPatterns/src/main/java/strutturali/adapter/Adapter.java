package strutturali.adapter;

public class Adapter implements Pos {

    private Point point;

    public Adapter(Point point) {
        this.point = point;
    }

    @Override
    public void printPosition() {
        System.out.printf("(%d, %d)", point.getX(), point.getY());
    }
}
