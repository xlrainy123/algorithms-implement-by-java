package lambda.some_functional_interface;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void filter(String[] strs, Predicate predicate){
        for (String str : strs) {
            if (predicate.test(str)){
                System.out.println(str);
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = {"hello", "world", "zhan", "san", "bu"};
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3 ? true : false;
            }
        };

        PredicateDemo.filter(strs, predicate);
    }

}

