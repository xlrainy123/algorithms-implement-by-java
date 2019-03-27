package reflect;


class StaticAndFinal {
    public static final int a = 1;
    static int b = 2;

    static {
        System.out.println("初始化!");
    }
}

public class StaticFinal {
    public static void main(String[] args) {
        System.out.println(StaticAndFinal.b);
    }
}
