package design_pattern.proxy;

public class EatImpProxy implements Eatable{

    private Eatable eatable;

    public EatImpProxy(Eatable eatable){
        this.eatable = eatable;
    }
    public void work(){
        System.out.println("吃饭之前先洗手!");
        eatable.work();
        System.out.println("吃完饭后要洗碗!");
    }

    public void work(String name){
        System.out.println("吃饭之前先洗手!");
        eatable.work(name);
        System.out.println("吃完饭后要洗碗!");
    }
}
