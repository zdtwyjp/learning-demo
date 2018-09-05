package org.tech.java.jdk.jdk8.functionalInterface;

import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void test() {
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(100);
    }
}
