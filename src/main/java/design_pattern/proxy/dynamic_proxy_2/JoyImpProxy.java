package design_pattern.proxy.dynamic_proxy_2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JoyImpProxy implements InvocationHandler {

    private Object joyImp;

    JoyImpProxy(Object joyImp){
        this.joyImp = joyImp;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws  Exception{
        System.out.println(proxy.getClass().getName());
        System.out.println(method);
        System.out.println(args);
        return method.invoke(joyImp, args);
    }

    public Object newProxyInstance(){
        return Proxy.newProxyInstance(joyImp.getClass().getClassLoader(),joyImp.getClass().getInterfaces(),this);
    }
}
