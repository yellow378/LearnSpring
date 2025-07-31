package org.cn.lwx;

public class TreadLocalTest {


    public static void main(String[] args) throws InterruptedException {
        InheritableThreadLocal local = new InheritableThreadLocal();
        local.set("abc");
        Thread thread = new Thread(()->{
            System.out.println(local.get());
            local.set("def");
            System.out.println(local.get());
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println(local.get());
    }
}
