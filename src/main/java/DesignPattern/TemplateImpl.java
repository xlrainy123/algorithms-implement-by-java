package DesignPattern;

public class TemplateImpl extends Template{

    public void step1(){
        System.out.println("1");
    }

    public void step2(){
        System.out.println("2");
    }

    public void step3(){
        System.out.println("3");
    }

    public static void main(String[] args){
        TemplateImpl im = new TemplateImpl();
        im.dosomething();
    }
}
