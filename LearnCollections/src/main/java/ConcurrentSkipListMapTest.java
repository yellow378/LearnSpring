import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTest {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> concurrentSkipListMap = new ConcurrentSkipListMap<>((o1, o2) -> o2 - o1);
        concurrentSkipListMap.put(1, "one");
        concurrentSkipListMap.put(2, "two");
        concurrentSkipListMap.put(3, "three");
        System.out.println(concurrentSkipListMap);
        System.out.println(concurrentSkipListMap.get(1));
        System.out.println(concurrentSkipListMap.ceilingEntry(1));
    }
}
