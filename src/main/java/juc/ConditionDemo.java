package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    static class MyQueue<T>{

        private Object[] data;
        private int rear;
        private int front;

        public MyQueue(int size){
            data = new Object[size+1];
        }

        public boolean isEmpty(){
            if (front == rear){
                return true;
            }
            return false;
        }

        public boolean isFull(){
            if ((rear+1) % data.length == front)
                return true;
            return false;
        }

        public void offer(T o){
            if (isFull()){
                throw new IndexOutOfBoundsException();
            }
            data[rear] = o;
            rear = (rear+1) % data.length;
        }

        public T poll(){
            if (isEmpty()){
                throw new NoSuchElementException();
            }
            T t = (T)data[front];
            front = (front+1) % data.length;
            return t;
        }
    }

    private MyQueue<Integer> queue;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public ConditionDemo(MyQueue queue, Lock lock){
        this.queue = queue;
        this.lock = lock;
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void add(int o) throws InterruptedException{
        lock.lock();
        try{
            while (queue.isFull()){
                notFull.await();
            }
            queue.offer(o);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public int remove() throws InterruptedException{
        lock.lock();
        int t = 0;
        try{
            while(queue.isEmpty()){
                notEmpty.await();
            }
            t = queue.poll();
            notFull.signal();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args){

        MyQueue<Integer> queue = new MyQueue(10);
        Lock lock = new ReentrantLock();
        ConditionDemo demo = new ConditionDemo(queue, lock );

        Thread pro = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("生产者开始："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(3000);
                    demo.add(1);
                }catch (InterruptedException e){

                }
                System.out.println("生产者结束："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("消费者开始："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try{
                    System.out.println("----消费者取到的数据是："+demo.remove());
                }catch (InterruptedException e){

                }
                System.out.println("消费者结束："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        });

        pro.start();
        consumer.start();
//        new ConcurrentLinkedQueue<>()
//        pro.join();
//        ArrayBlockingQueue
//        consumer.join();
    }
}
