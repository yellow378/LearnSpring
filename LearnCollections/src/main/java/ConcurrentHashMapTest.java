import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "one");
        concurrentHashMap.put(2, "two");
        System.out.println(concurrentHashMap.get(1));
        if(concurrentHashMap.containsKey(3)) {
            System.out.println(concurrentHashMap.get(3));
        }
        concurrentHashMap.computeIfAbsent(3, key -> "three");
        concurrentHashMap.computeIfPresent(1, (key, value) -> value + "1");
        System.out.println(concurrentHashMap);
    }
}
