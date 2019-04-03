package generic;

public abstract class GenericClazz <E> {
    private E object;

    public E getObject() {
        return object;
    }

    public void setObject(E object) {
        this.object = object;
    }

    public GenericClazz(E object){
        this.object = object;
    }

    protected abstract void sayHello();

    protected abstract <T> void genericMethod(T obj);
}
