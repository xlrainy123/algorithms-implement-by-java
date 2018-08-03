package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    class Job1 extends Thread{
        private CountDownLatch count;
        public Job1(CountDownLatch count){
            this.count = count;
        }
        public void run(){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){}
            System.out.println("1");
            count.countDown();
        }
    }

    class Job2 extends Thread{
        private CountDownLatch count;
        public Job2(CountDownLatch count){
            this.count = count;
        }
        public void run(){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){}
            System.out.println("2");
            count.countDown();
        }
    }

    class Job3 extends Thread{
        private CountDownLatch count;
        public Job3(CountDownLatch count){
            this.count = count;
        }
        public void run(){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){}
            System.out.println("3");
            count.countDown();
        }
    }

    private CountDownLatch count;

    public CountDownLatchDemo(CountDownLatch count){
        this.count = count;
    }


    public void start() throws InterruptedException{

        Job1 job1 = new Job1(count);
        Job2 job2 = new Job2(count);
        Job3 job3 = new Job3(count);

        job1.start();
        job2.start();
        job3.start();

        System.out.println("主线程开始等待:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        count.await();
        System.out.println("主线程执行结束:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    public static void main(String[] args) throws InterruptedException{
        CountDownLatchDemo demo = new CountDownLatchDemo(new CountDownLatch(3));
        demo.start();
    }
}
