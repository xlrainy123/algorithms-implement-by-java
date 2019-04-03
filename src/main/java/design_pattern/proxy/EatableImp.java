package design_pattern.proxy;

public class EatableImp implements Eatable{
    public void work(){
        System.out.println("start eat!");
    }

    public void work(String name){
        System.out.println(name +" start eat!");
    }
}
