package strutturali.composite;

public class Client {

    public static void main(String[] args) {

        Group main = new Group();
        main.add(new Circle());
        main.add(new Triangle());

        Group group = new Group();
        group.add(new Circle());
        group.add(new Triangle());
        main.add(group);

        main.add(new Triangle());
        main.add(new Group());

        main.print();
    }
}
