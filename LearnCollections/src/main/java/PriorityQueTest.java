import java.util.PriorityQueue;

public class PriorityQueTest {
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        pq.add("A");
        pq.add("B");
        pq.add("C");
        pq.add("D");
        pq.add("E");
        System.out.println(pq);
    }
}
