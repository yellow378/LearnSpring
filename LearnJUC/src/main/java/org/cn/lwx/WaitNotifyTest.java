package org.cn.lwx;

public class WaitNotifyTest {



    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+": wait");
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+": notified");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+": notify another thread");
            synchronized (lock2){
                synchronized (lock){
                    lock.notify();
                    try {
                        Thread.sleep(2000);
                        System.out.println("sleep ok");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();

    }
}
