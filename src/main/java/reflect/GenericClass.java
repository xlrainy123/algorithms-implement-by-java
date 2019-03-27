package reflect;


import java.util.ArrayList;
import java.util.List;

interface Flyable {
    void fly();
}

interface Paper {
    void write();
}

class People implements Flyable {
    static {
        System.out.println("people initial!");
    }

    public String name;
    public int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void fly() {
        System.out.println("Person is fly!");
    }
}

class Doctor extends People
        implements Paper {
    static public int termId;

    static {
        System.out.println("Doctor initial!");
    }

    public Doctor() {
    }

    public Doctor(String name, int age, int term) {
        this.name = name;
        this.age = age;
        this.termId = term;
    }

    @Override
    public String toString() {
        return Integer.toString(termId++);
    }

    public void write() {
        System.out.println("Doctor is writting!");
    }
}

public class GenericClass<T> {

    private Class<T> type;

    public GenericClass(Class<T> type) {
        this.type = type;
    }

    public static void main(String[] args) throws Exception {
        Class<Doctor> doctorClass = Doctor.class;
        Doctor doctor = doctorClass.newInstance();
        doctor.write();
        doctor.fly();

        System.out.println("-------------");
        /**
         * ? super Doctor: 表示Doctor的父类
         */
        Class<? super Doctor> up = doctorClass.getSuperclass();
        System.out.println(up.getName());

        System.out.println("------------------------\n");
        GenericClass<Doctor> genericClass = new GenericClass<>(Doctor.class);
        genericClass.create(12);
    }

    public List<T> create(int num) {
        List<T> results = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            try {
                results.add(type.newInstance());
            } catch (IllegalAccessException e) {
                System.out.println("IllegalAccessException");
            } catch (InstantiationException e) {
                System.out.println("InstantiationException");
            }
        }
        System.out.println(results);
        return results;
    }

}
