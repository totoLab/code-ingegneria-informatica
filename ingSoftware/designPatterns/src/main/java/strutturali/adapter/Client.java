package strutturali.adapter;

public class Client {

    public static void main(String[] args) {
        Position position = new Position(10, 10);
        printPosition(position);

        Point point = new Point(10, 10);
        // printPosition(point); error
        Adapter adapter = new Adapter(point);
        printPosition(adapter);
    }

    static void printPosition(Pos position) {
        position.printPosition();
    }
}
