package netty.time;

public class TimeServer {

    public static void main (String[] args){
        TimeServerReactor timeServerReactor = new TimeServerReactor();
        Thread thread = new Thread(timeServerReactor, "time-server-001");
        thread.start();
    }
}
