package design_pattern.proxy.dynamic_proxy_2;

public class JoyImp implements Joy{
    private String who;
    public void work(){
        System.out.println("自己玩~");
    }
    public void workWith(){
        System.out.println("和"+who+"一起玩~");
    }
    public void test(){}
    public void test(String name){System.out.println("hello");}
    public JoyImp(String name){
        this.who = name;
    }
}
