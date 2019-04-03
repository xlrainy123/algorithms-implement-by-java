package design_pattern.proxy.dynamic_proxy_3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TargetInvocationHandler<T> implements InvocationHandler {

    private T realImp;
    public TargetInvocationHandler(T realImp){
        this.realImp = realImp;
    }
    public T newProxyInstance(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception{
        System.out.println(proxy.getClass().getName());
        System.out.println("method.getName:"+method.getName()+
                            ",method.getParameterTypes"+method.getParameterTypes());
        if (args == null){
            return method.invoke(realImp, args);
        }
        for (Object param : args){
            System.out.println("param:"+param);
        }
        return method.invoke(realImp, args);
    }
}
