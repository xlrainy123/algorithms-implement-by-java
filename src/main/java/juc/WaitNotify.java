package juc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wait()
 *  object对象调用wait()方法后，会主动释放之前获取的锁，然后进入等待状态
 *
 *  从wait方法返回的前提是：
 *  1、获取了对象锁
 *
 *  notify()方法被调用后，并不会立刻释放锁
 *  当一个线程A调用notify()时，等待的线程并不会立刻从wait方法中返回，只有当线程A释放锁之后，该线程才有机会从wait()中返回。
 */
public class WaitNotify {

    public volatile boolean flag = false;
    public Object lock = new Object();

    public void start(){

//        Thread.currentThread().setDaemon(true);

        Wait wait = new Wait();
        wait.start();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Notify notify = new Notify();
        notify.start();
    }

    public static void main(String[] args){
        WaitNotify waitNotify = new WaitNotify();
        waitNotify.start();
    }

    class Wait extends Thread {
        @Override
        public void run(){
            System.out.println("wait线程开始执行！");
            synchronized(lock){
                while (!flag){
                    System.out.println("wait线程："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("wait线程执行结束："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    class Notify extends Thread{
        @Override
        public void run(){
            System.out.println("notify线程开始执行！");
            synchronized(lock){
                System.out.println("notify线程："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                flag = true;
                lock.notify();
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            synchronized (lock){
                System.out.println("notify线程再次加锁："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
