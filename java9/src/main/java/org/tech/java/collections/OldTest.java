package org.tech.java.collections;

import org.junit.Test;

import java.util.*;

public class OldTest {
    @Test
    public void test() {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set = Collections.unmodifiableSet(set);
        System.out.println(set);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list = Collections.unmodifiableList(list);
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("A", "Apple");
        map.put("B", "Boy");
        map.put("C", "Cat");
        map = Collections.unmodifiableMap(map);
        System.out.println(map);
    }
}
