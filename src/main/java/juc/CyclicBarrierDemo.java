package juc;

import java.util.Map;
import java.util.concurrent.*;

public class CyclicBarrierDemo implements Runnable{

    private ConcurrentHashMap<String, Integer> result = new ConcurrentHashMap<>();

    private CyclicBarrier barrier;

    public CyclicBarrierDemo(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public CyclicBarrierDemo(){}

    public void count(){
        for (int i = 0; i < 4; i++){
            Count count = new Count(barrier);
            count.start();
        }
    }

    public void run(){
        int cnt = 0;
        for (Map.Entry<String, Integer> one : result.entrySet()){
            cnt += one.getValue();
        }
        System.out.println("最后的结果是："+cnt);
    }

    class Count extends Thread{

        public int start;
        private CyclicBarrier barrier;

        public Count(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        public void run(){
            int cnt = 0;
            System.out.println(Thread.currentThread().getName()+", start = "+start);
            for (int i = 1; i <= 5; i++){
                cnt += i;
            }
            result.put(Thread.currentThread().getName(), cnt);
            try{
                barrier.await();
            }catch (Exception e){e.printStackTrace();}
        }
    }


    public static void main(String[] args){
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.barrier = new CyclicBarrier(4, demo);
        demo.count();
    }
}
