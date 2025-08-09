import java.io.*;
import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("abc");
        if(set.contains("abc")){
            System.out.println("set contains abc");
        }

        // 序列化 HashSet
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hashset.ser"))) {
            oos.writeObject(set);
            System.out.println("HashSet 序列化成功，保存到 hashset.ser 文件");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化 HashSet
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hashset.ser"))) {
            HashSet<String> deserializedSet = (HashSet<String>) ois.readObject();
            System.out.println("HashSet 反序列化成功");
            if(deserializedSet.contains("abc")){
                System.out.println("反序列化后的 set contains abc");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}