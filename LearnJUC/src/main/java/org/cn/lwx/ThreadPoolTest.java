package org.cn.lwx;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service =  Executors.newSingleThreadExecutor();
        Future<?> future = service.submit(()->{
            System.out.println(Thread.currentThread().getName());
            int i = 0;
            int t = 3/i;
        });
        Future<?> future2 = service.submit(()->{
            System.out.println(Thread.currentThread().getName());
            int i = 0;
            int t = 3/i;
        });
        try{
            Object o = future.get();
            System.out.println(o);
        }catch (Exception e){
            System.out.println("catched: "+e.getMessage());
        }
        service.execute(()->{
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("abc");
        });
        service.shutdownNow();
    }
}
