package lambda;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main (String[] args){
        String[] strs = "zhangsy is sha bi".split(" ");
        long cnt = Arrays.stream(strs)
                .filter((str)->{
                    System.out.println("hello");
                    return str.length()>2;
                })
                .count();
        System.out.println(cnt);
        Arrays.stream(strs)
                .filter((str)->{
                    System.out.println("hello");
                    return str.length()>2;
                });
    }
}
