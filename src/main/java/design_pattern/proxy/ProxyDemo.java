package design_pattern.proxy;

public class ProxyDemo {

    public static void main(String[] args){
       Eatable proxy = new EatImpProxy(new EatableImp());
       proxy.work();
    }
}
