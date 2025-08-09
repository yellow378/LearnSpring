import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<MyObject, String> treeMap = new TreeMap<>((o1, o2) -> {
            if (Objects.equals(o1.getId(), o2.getId())) {
                return 0;
            }
            return o1.getId() < o2.getId() ? -1 : 1;
        });
        MyObject o1 = new MyObject(1,"one");
        MyObject o2 = new MyObject(2,"two");
        MyObject o3 = new MyObject(3,"three");
        treeMap.put(o1, "one");
        treeMap.put(o2, "two");
        treeMap.put(o3, "three");

        Map.Entry higherEntry =  treeMap.higherEntry(o1);
        System.out.println(higherEntry.getKey()+" "+higherEntry.getValue());
        System.out.println("=======================");

        for(Map.Entry<MyObject, String> entry : treeMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("=======================");

        SortedMap<MyObject, String> m2 = treeMap.subMap(o1,o2);
        for(Map.Entry<MyObject, String> entry : m2.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("=======================");

    }
    private static class MyObject {
        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public Integer getId() {
            return this.id;
        }

        private Integer id;
        private String name;
        MyObject(int id, String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public String toString(){
            return id+" "+name;
        }

        public boolean equals(Object o){
            if(o instanceof MyObject){
                return this.toString().equals(o.toString());
            }
            return false;
        }
        public int hashCode(){
            return this.toString().hashCode();
        }
    }
}
