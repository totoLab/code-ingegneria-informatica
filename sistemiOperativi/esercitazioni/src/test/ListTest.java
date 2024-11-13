package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>( Arrays.asList(1, 2, 3, 4, 5, 6) );
        Iterator<Integer> iterator = list.iterator();

        for (int i = 0; i < list.size() / 2; i++) {
            iterator.next();
        }
        list.add(7);
        iterator.next();
    }
}
