package design_pattern.proxy.dynamic_proxy_3;
public class TargetInterfaceImp implements TargetInterface{

    public void sayHello(){
        System.out.println("hello everyone!");
    }

    public void sayHelloTo(String who){
        System.out.println("hello,"+who);
    }

    public String getHello(String hello){
        return "hello";
    }

}
