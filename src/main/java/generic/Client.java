package generic;

public class Client {



    public static void main(String[] args){
        GenericClazzExtend<Person> clzz = new GenericClazzExtend<>(
                new Person("zhangsy",12), "xlcheng");
        clzz.sayHello();
        clzz.genericMethod(12);
    }
}
