package collection.queue;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    private Object[] data;
    private int rear;
    private int front;

    public MyQueue(int size){
        data = new Object[size+1];
    }

    public void offer(T o){
        if ((rear+1)%data.length == front){
            throw new IndexOutOfBoundsException();
        }
        data[rear] = o;
        rear = (rear+1) % data.length;
        System.out.println("rear = "+rear+", front = "+front);
    }

    public boolean isEmpty(){
        if (front == rear)
            return true;
        return false;
    }

    public T poll(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T t = (T)data[front];
        front = (front+1) % data.length;
        System.out.println("rear = "+rear+", front = "+front);
        return t;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i%data.length != rear; i = (i+1)%data.length){
//            System.out.println("i = "+i);
            sb.append(data[i]+",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args){
        MyQueue<Integer> queue = new MyQueue<>(4);
        System.out.println(queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
//        collection.queue.offer(4);
        System.out.println(queue);
        System.out.println("出队："+queue.poll());
        System.out.println("出队："+queue.poll());
        System.out.println("出队："+queue.poll());

        System.out.println(queue);

//        System.out.println("出队："+collection.queue.poll());

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue);

        System.out.println("出队："+queue.poll());
    }
}
