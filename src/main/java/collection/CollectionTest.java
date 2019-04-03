package collection;

import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args){
        List<Integer> ints = Arrays.asList(1,2,3,4);
        System.out.println(ints);
        ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(8);
        ints.add(4);
        ints.add(5);
        ints.add(7);
        ints.add(3);

        System.out.println(ints);

        List sub = ints.subList(0,4);
        System.out.println(sub);
        System.out.println(ints.containsAll(sub));

        Collections.sort(sub);
        System.out.println(sub);
        System.out.println(ints.containsAll(sub));
        System.out.println(ints);

    }
}
