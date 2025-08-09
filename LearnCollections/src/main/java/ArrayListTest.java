import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {



    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for(Iterator<Integer> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println();

        list.forEach(System.out::println);

        list.remove(1);
        for(Integer i : list){
            System.out.println(i);
        }
        List<Integer> list1 = Collections.synchronizedList(list);
    }
}