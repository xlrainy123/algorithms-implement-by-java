package generic;

public class Person {
    String name;
    int age;
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("name:");
        sb.append(name);
        sb.append(",age:");
        sb.append(age);
        sb.append("]");
        return sb.toString();
    }
}
