package design_pattern.proxy.dynamic_proxy_2;

import java.lang.reflect.Method;

public class JoyClient {

    public static void main(String[] args) throws Exception{
        Joy joy = new JoyImp("zhangsy");
        joy.work();
        joy.workWith();
        System.out.println(joy.getClass());
        Method method = joy.getClass().getMethod("test", String.class);
        System.out.println(method);
        method.invoke(joy, "zhangsy");
//        Joy joy1 = (Joy) Proxy.newProxyInstance(Joy.class.getClassLoader(),
//                JoyImp.class.getInterfaces(), new JoyImpProxy(joy));
//        joy1.workWith();
//        Joy joy1 = (Joy) new JoyImpProxy(joy).newProxyInstance();
//        joy1.workWith();
    }
}
