package org.cn.lwx;

import java.util.concurrent.*;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        Thread thead = new Thread(() -> {
            synchronized (MainClass.class) {
                try {
                    System.out.println("开始等待");
                    MainClass.class.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("线程开始");

        });
        thead.start();
        Thread.sleep(2000);
        synchronized (MainClass.class) {
            MainClass.class.notify();
        }
    }
}
