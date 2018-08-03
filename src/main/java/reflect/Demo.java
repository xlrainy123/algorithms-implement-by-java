package reflect;

public class Demo {

    public String name;
    public int age;

    public Demo(String name, int age){
        this.age = age;
        this.name = name;
    }

    public Demo(){}

    public int getAge() {
        return age;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(){
        System.out.println("name = "+name+", age = "+age);
    }
}
