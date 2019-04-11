package polymorphism;

public class A {
    public void show(A obj){
        System.out.println("A and A");
    }

    public void test(){

    }

    public static void main(String[] args){
        A a1 = new A();
        A a2 = new B();
        B b = new B();

        a2.show(a1);
    }

}

class B extends A{
    public void show(A obj){
        System.out.println("B and A");
    }

    public void show(B obj){
        System.out.println("B and B");
    }
}