package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用join()方法实现一个多米诺骨牌的程序
 */
public class JoinDemo{

    class Dominuo extends Thread{

        public Thread previous;

        public Dominuo(Thread previous, String name){
            this.previous = previous;
            super.setName(name);
        }

        public void run(){
            try{
                previous.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行结束");
        }
    }

    public void start() throws InterruptedException{

        Thread previous = Thread.currentThread();
        for (int i = 1; i <= 20; i++){
            Dominuo current = new Dominuo(previous, String.valueOf(i));
            previous = current;
            current.start();
        }
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"执行结束");
    }

    public static void main(String[] args)throws InterruptedException{
        JoinDemo demo = new JoinDemo();
        demo.start();


        Lock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock(2, TimeUnit.SECONDS);
        lock.lockInterruptibly();
    }
}
