package netty.time;

public class TimeClient {

    public static void main (String[] args){
        TimeClientHandler handler = new TimeClientHandler();
        Thread thread = new Thread(handler, "client-001");
        thread.start();
    }
}
