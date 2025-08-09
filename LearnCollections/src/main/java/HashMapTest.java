import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<MyObject, String> map = new HashMap<>();
        MyObject o1 = new MyObject(1,"one");
        MyObject o2 = new MyObject(2,"two");
        MyObject o3 = new MyObject(3,"three");
        map.put(o1, "one");
        map.put(o2, "two");
        map.put(o3, "three");

        for(Map.Entry<MyObject, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        o3.setId(4);
        map.put(o3,"four");
        for(Map.Entry<MyObject, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    @Getter
    private static class MyObject {
        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
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
