package juc;

public class DaemonDemo {
    /**
     * 这是实验知道当JVM中不存在守护线程的守护，所有的守护线程会被强制退出
     * @param args
     */
    public static void main(String[] args){
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                    System.out.println("守护");
                }catch (InterruptedException e){

                }finally {
                    System.out.println("守护线程执行结束");
                }
            }
        });

        daemon.setDaemon(true);

        Thread runner = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(6000);
                }catch (InterruptedException e){

                }
            }
        });

        runner.start();
        daemon.start();
    }



}
