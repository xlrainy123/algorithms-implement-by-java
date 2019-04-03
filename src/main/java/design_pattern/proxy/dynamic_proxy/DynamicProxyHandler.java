package design_pattern.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("proxy: "+proxy.getClass());
        System.out.println("method: "+method.toString());
        if (args == null || args.length == 0){
            System.out.println("No args....");
        } else {
            for (Object arg : args){
                System.out.println(arg);
            }
        }
        return method.invoke(proxied, args);
    }
}
