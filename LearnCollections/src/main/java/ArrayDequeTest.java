import java.util.ArrayDeque;

public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offerFirst(3);
        deque.push(2);
        System.out.println(deque.pop());
        System.out.println(deque);
    }
}
