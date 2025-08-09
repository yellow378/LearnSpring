import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueTest {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedTask(()->{
            System.out.println("task1");
        },1000));
        delayQueue.put(new DelayedTask(()->{
            System.out.println("task2");
        },500));
        while(!delayQueue.isEmpty()){
            try{
                delayQueue.take().getTask().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("all task done");
    }
    public static class DelayedTask implements Delayed {
        Runnable task;
        long delayTime;

        public Runnable getTask() {
            return this.task;
        }

        public DelayedTask(Runnable task, long delayTime) {
            this.task = task;
            this.delayTime = delayTime + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
