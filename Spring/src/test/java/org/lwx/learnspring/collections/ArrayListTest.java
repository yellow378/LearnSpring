package org.lwx.learnspring.collections;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class ArrayListTest {
    @Test
    public void test1(){
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add(null);
        System.out.println(list);
        Assert.assertEquals("abc", list.get(0));
        Assert.assertEquals(3,list.size());
    }
    @Test
    public void test2() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 添加元素
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() -> {
            // 迭代元素 (注意：Integer 是不可变的，这里的 i++ 不会修改 list 中的值)
            for (Integer i : list) {
                System.out.println(i);
                i++; // 这行代码实际上没有修改list中的元素
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("删除元素1");
            list.remove(Integer.valueOf(1)); // 使用 Integer.valueOf(1) 删除指定值的对象
            countDownLatch.countDown();
        });

        t1.start();
        t2.start();
        countDownLatch.await();
    }

    @Test
    public void test3() throws InterruptedException {
        // 使用线程安全的 CopyOnWriteArrayList 避免 ConcurrentModificationException
        List<Integer> list = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 添加元素
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() -> {
            // 迭代元素 (注意：Integer 是不可变的，这里的 i++ 不会修改 list 中的值)
            for (Integer i : list) {
                System.out.println(i);
                i++; // 这行代码实际上没有修改list中的元素
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("删除元素1");
            list.remove(Integer.valueOf(1)); // 使用 Integer.valueOf(1) 删除指定值的对象
            countDownLatch.countDown();
        });

        t1.start();
        t2.start();
        countDownLatch.await();
    }

}
