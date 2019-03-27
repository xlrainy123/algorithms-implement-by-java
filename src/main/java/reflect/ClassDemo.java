package reflect;

interface Eatable {
    void run();
}

class Person {

    static {
        System.out.println("Person is init！");
    }

    String name;
    int age;

    Person() {
    }          // 默认构造器

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student extends Person
        implements Eatable {
    String stuId;

    public Student() {
    }      //默认构造器

    public Student(String name, String stuId, int age) {
        super(name, age);
        this.stuId = stuId;
    }

    public void run() {
        System.out.println("ClassDemo is run!");
    }
}

public class ClassDemo {

    public static void info(Class cc) {
        System.out.println("class name is: " + cc.getName());
        System.out.println("class simple name is: " + cc.getSimpleName());
        System.out.println("class is interface?" + " [" + cc.isInterface() + "]");
        System.out.println("------------------------------");
    }

    public static void getAllSuperClass(Class c) {
        try {
            c = Class.forName("reflect.Student");
        } catch (ClassNotFoundException e) {
            System.out.println("The class Student is not found!");
            System.exit(1);
        }
        Class up = c.getSuperclass();
        while (up != null) {
            info(up);
            up = up.getSuperclass();
        }
    }

    public static void printInfo(Class c) {
        try {
            c = Class.forName("reflect.Student");
        } catch (ClassNotFoundException e) {
            System.out.println("The class Student is not found!");
            System.exit(1);
        }
        info(c);

        for (Class inter : c.getInterfaces()) {
            info(inter);
        }

        Class up = c.getSuperclass();
        info(up);
        Object object = null;
        try {
            object = up.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (object != null) {
            System.out.println(object.getClass());
        }
        if (object instanceof Person) {
            Person p1 = (Person) object;
            System.out.println("name; " + p1.name + ",   age: " + p1.age);
        }

        System.out.println("-------------------所有父类--------------");
        try {
            c = Class.forName("reflect.Student");
        } catch (ClassNotFoundException e) {
            System.out.println("The class Student is not found!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
//        Class c = null;
//        printInfo(c);
//        getAllSuperClass(c);
        Class x = Student.class;
        try {
            x = Class.forName("reflect.Student");
        } catch (ClassNotFoundException e) {
            System.out.println("The class Student is not found!");
            System.exit(1);
        }
    }
}


