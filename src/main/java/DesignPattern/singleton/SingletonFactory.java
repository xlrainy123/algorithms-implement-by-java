package DesignPattern.singleton;

public class SingletonFactory {
    /**
     * 最简单的单例模式
     * 但是这种模式会出现问题：在多线程环境下，如果线程A执行到1处，线程B执行到2处的话，
     * 这时线程B还没有完成初始化，那么线程A也会去执行初始化，
     * 这样一来，就不是单例了
     */
    private Singleton instance = null;

    private volatile Singleton instance2 = null;

    public Singleton getInstance(){
        if (instance == null){         //1
            return new Singleton();     //2
        }
        return instance;
    }


    /**
     * 双重检查锁定
     * 但是这种方式在多线程环境下也是会出现问题的。
     * 因为重排序的存在，执行  instance = new Singleton()的时候，实际上是分三步来完成的。
     * 1、分配内存
     * 2、完成初始化
     * 3、将引用变量的值指向内存地址
     * 但是，2和3可能会发生重排序，使得在判断不为null的时候，线程访问了一个尚未完成初始化的对象
     */

    public Singleton getInstance2(){
        if (instance == null){
            synchronized (SingletonFactory.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


    /**
     * 接下来介绍两种线程安全的单例模式的实现
     * instance2是一个volatile修饰的变量，在这个对象进行初始化的时候，第三步将引用变量指向内存地址，
     * 这是一个volatile变量的写操作，那么处理器会严格限制2和3的重排序，因为volatile变量的写操作不可以和之前的任何操作发生重排序
     */
    public Singleton getInstance3(){
        if (instance2 == null){     //private volatile Singleton instance2 = null;
            synchronized (SingletonFactory.class){
                if (instance2 == null){
                    instance2 = new Singleton();
                }
            }
        }
        return instance2;
    }

    /**
     * 延迟加载的方式，利用类初始化时候，静态成员只会被初始化一次，每个类的初始化都会对应一个初始化锁
     */

    private static class InstanceHolder{
        private static Singleton instance = new Singleton();
    }

    /**
     * 静态成员被调用的时候，会触发类的初始化，而InstanceHolder.instance又是一个
     * 静态成员，因此他只会被初始化一次，可以实现单例模式
     * @return
     */
    public static Singleton getInstance4(){
        return InstanceHolder.instance;
    }
}
