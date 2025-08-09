import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class CopyOnWriteArrayTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Thread thread2 = new Thread(() -> {
          for(int i = 100; i < 200; i++){
              list.add(i);
              System.out.println("Thread2:"+list.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
          latch.countDown();
        });
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                list.add(i);
                System.out.println("Thread1:"+list.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            latch.countDown();
        });
        thread2.start();
        thread.start();
        latch.await();
        System.out.println("main:"+list.size());
    }
}
