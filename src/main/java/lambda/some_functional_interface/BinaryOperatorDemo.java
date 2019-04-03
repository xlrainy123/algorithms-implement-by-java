package lambda.some_functional_interface;


import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {
    BinaryOperator<String> operator = new BinaryOperator<String>() {
        @Override
        public String apply(String s, String s2) {
            s = s+s2;
            System.out.println(s);
            return s;
        }
    };

    public void apply(String[] strings, BinaryOperator operator){
        String s = "x";
        for (int i = 0; i < strings.length; i++){
            operator.apply(s, strings[i]);
        }
    }
    public static void main(String[] args) {
        BinaryOperatorDemo demo = new BinaryOperatorDemo();
        demo.apply("1 2 3".split(" "), demo.operator);
    }
}
