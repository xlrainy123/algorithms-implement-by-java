package lambda;

class Person{
    String name;
    Person(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
public class LambdaDemo {
    static Person person = new Person("zhangsy");
    public static void main (String[] args){
        final int a = 0;  //  在匿名內部類中需要被使用的變量，一定要是final修飾的
        String name = person.getName(); // 或者是要effectively final修飾的 (既定事實的final)
//        name = name+"1";   這是錯誤的用法, 因為在下面的匿名內部類或者lambda表達式中使用了這個變量
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(name);
                System.out.println("hello world");
            }
        });

        Thread second = new Thread(()->{
            int x = a;
            for (int i = 0; i < 10; i++){
                x += i;
            }
            System.out.println(name); //這就是“閉包”的概念
            System.out.println(x);
        });

        first.start();
        second.start();
    }
}
