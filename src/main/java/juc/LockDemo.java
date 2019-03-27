package juc;


import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public Job job;

    public LockDemo() {
        job = new Job(new ReentrantLock());
    }

    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        Thread writer = new Thread(new Writer(demo.job), "writer thread");
        Thread reader = new Thread(new Reader(demo.job), "reader thread");
        reader.start();
        writer.start();
//        reader.start();
    }

    static class Writer implements Runnable {
        public Job job;

        public Writer(Job job) {
            this.job = job;
        }

        public void run() {
            job.write();
        }
    }

    static class Reader implements Runnable {
        public Job job;

        public Reader(Job job) {
            this.job = job;
        }

        public void run() {
            job.read();
        }
    }

    class Job {
        public ReentrantLock lock;

        Job(ReentrantLock lock) {
            this.lock = lock;
        }

        public void write() {
            lock.lock();
            try {
                System.out.println("write job start!");
            } finally {
                lock.unlock();
            }
        }

        public void read() {
            lock.lock();
            try {
                System.out.println("read job start!");
            } finally {
                lock.unlock();
            }
        }
    }
}
