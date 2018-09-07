package org.tech.java.collections;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewTest {
    @Test
    public void test() {
        Set<String> set = Set.of("A","B","C");
        System.out.println(set);
        List<String> list = List.of("A","B", "C");
        System.out.println(list);
        Map<String, String> map = Map.of("A","Apple");
        System.out.println(map);
        Map<String, String> map1 = Map.ofEntries(
                new AbstractMap.SimpleEntry<String, String>("A", "Apple")
        );
        System.out.println(map1);

    }
}
