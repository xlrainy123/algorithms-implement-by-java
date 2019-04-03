package design_pattern.proxy.dynamic_proxy_3;

public class Client {

    public static void main(String[] args){
        TargetInvocationHandler<TargetInterfaceImp> handler = new TargetInvocationHandler<>(new TargetInterfaceImp());
        TargetInterface target = handler.newProxyInstance(TargetInterfaceImp.class);
        target.sayHelloTo("zhangsy");
    }
}
