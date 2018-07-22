package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 以多线程计数为例
 * 20个线程累加一个计数器
 */
public class SynchronizedDemo {

    public volatile int cnt = 0;
    public AtomicInteger count = new AtomicInteger(0);
    public void count() throws InterruptedException{

        for (int i = 0; i < 20; i++){
            Thread thread = new Thread(new CountThread(), (i+1)+"");
            thread.start();
        }

        Thread.currentThread().sleep(3000);
        System.out.println("最终的结果是cnt="+count);
    }

    class CountThread implements Runnable{

        @Override
        public void run(){
            for (int i = 0; i < 100; i++){
//                count.getAndIncrement();
                int val;
                do{
                    val = count.get();
                }while (!count.compareAndSet(val, ++val));
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行结束！");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedDemo demo = new SynchronizedDemo();
        demo.count();
    }
}
