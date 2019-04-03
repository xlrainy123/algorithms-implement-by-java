package design_pattern.proxy.dynamic_proxy;

import design_pattern.proxy.Eatable;
import design_pattern.proxy.EatableImp;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void test(Eatable eatable){
        eatable.work();
    }

    public static void main(String[] args){
        EatableImp eatableImp = new EatableImp();
        test(eatableImp);

        System.out.println("--------------\n");

        Eatable dynamicProxy = (Eatable)Proxy.newProxyInstance(Eatable.class.getClassLoader(),
                new Class[]{Eatable.class}, new DynamicProxyHandler(eatableImp));

        test(dynamicProxy);
    }
}
