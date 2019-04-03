package generic;

public class GenericClazzExtend<E> extends GenericClazz<E>{
    public String name;
    public GenericClazzExtend(E object, String name){
        super(object);
        this.name = name;
    }

    protected void sayHello(){
        System.out.println(getObject());
        System.out.println(getObject().getClass().getName());
    }

    protected <T> void genericMethod(T obj){
       System.out.println( obj.toString());
    }
}
