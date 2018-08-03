package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor
                    (4,10,
                    3,TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(1),
                    new ThreadPoolExecutor.DiscardOldestPolicy());

    ExecutorService s = Executors.newFixedThreadPool(1);
    ExecutorService s2 = Executors.newSingleThreadExecutor();
    ExecutorService s3 = Executors.newCachedThreadPool();


    public void start() {
        for (int i = 0; i < 10; i++){
            s.execute(new Runnable(){
                public void run(){
                    System.out.println(Thread.currentThread().getName()+":"+
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e){}
                    System.out.println(Thread.currentThread().getName()+":"+
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                }
            });
        }

        s.shutdown();

    }

    public static void main(String[] args){

        ThreadPoolDemo demo = new ThreadPoolDemo();
        demo.start();

    }
}
