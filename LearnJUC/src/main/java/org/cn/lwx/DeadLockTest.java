package org.cn.lwx;

public class DeadLockTest {

    public static void main(String[] args){
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1000);
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(()->{
            synchronized (lock2){
                try{
                    Thread.sleep(1000);
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName());
                    }
                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
