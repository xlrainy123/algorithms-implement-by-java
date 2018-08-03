package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

    public void getConstructor(Class<?> clazz){

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor);
        }
    }
    public void getMethon(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            System.out.println(method);
        }
    }

    public void getField(Class<?> clazz){
        Field[] fields = clazz.getFields();
        for (Field field : fields){
            System.out.println(field);
        }
    }

    public Object creatObject(Class<?> clazz) throws Exception{
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        return constructor.newInstance("zhangsy", 10);
    }

    public void invokeMethod(Class<?> clazz) throws Exception{
        Method method = clazz.getMethod("getName");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object demo = constructor.newInstance("xlcheng", 19);
        System.out.println(method);
        method.invoke(demo);
    }

    public static void main(String[] args) throws Exception{
        ReflectDemo demo = new ReflectDemo();
        Class<?> clazz = Class.forName("reflect.Demo");
//        demo.getConstructor();
//        demo.getMethon();
//        demo.getField(clazz);

//        Demo test = (Demo) demo.creatObject(clazz);
//        System.out.println(test.age);
//        System.out.println(test.name);

//        System.out.println(test.getAge());

        demo.invokeMethod(clazz);
    }
}
