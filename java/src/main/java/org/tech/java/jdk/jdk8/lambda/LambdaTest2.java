package org.tech.java.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 集合排序
 */
public class LambdaTest2 {

    @Test
    public void t1() {
        List<String> features = Arrays.asList("001", "002", "003");
        // 传统方式
        System.out.println("----------------");
        for (String f: features) {
            System.out.println(f);
        }
        System.out.println("----------------");
        // 使用Lambda表达式
        features.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("----------------");
        features.forEach(f -> System.out.println(f));
    }


}
