package DesignPattern;

/**
 * 模板方法
 * 在父类中定义算法的主要架构，将算法的具体实现延迟到子类中实现。
 * 可以在不改变算法主体架构的前提下，根据不同的需求，设计不同的算法
 * 具有很好的可扩展性
 */
public class Template {

    protected final void dosomething(){
        step1();
        step2();
        step3();
    }

    protected void step1(){throw new UnsupportedOperationException();}
    protected void step2(){throw new UnsupportedOperationException();}
    protected void step3(){throw new UnsupportedOperationException();}

}
