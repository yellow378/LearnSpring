package org.lwx.learnspring.collections;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListTest {
    @Test
    public void synchronizedListTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> synchronizedList = Collections.synchronizedList(list);
        synchronizedList.add("2");
        System.out.println(synchronizedList);
        Assert.assertEquals(2, synchronizedList.size());
    }
}
