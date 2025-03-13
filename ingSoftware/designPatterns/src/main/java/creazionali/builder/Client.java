package creazionali.builder;

import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        ListBuilder builder = new NormalListBuilder(new ArrayList<>());

        builder.add(5)
                .add(6)
                .add(1)
                .sort()
                .add(2)
                .add(5)
                .add(5)
                .filter(5);
        System.out.println(builder);
    }
}
