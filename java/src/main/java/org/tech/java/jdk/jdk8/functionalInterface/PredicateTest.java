package org.tech.java.jdk.jdk8.functionalInterface;

import org.junit.Test;

import java.util.function.Predicate;

public class PredicateTest {

    @Test
    public void test() {
        printBigValue(10, val -> val > 5);
        printBigValueAnd(10, val -> val > 5);
    }

    public void printBigValue(int v, Predicate<Integer> predicate) {
        if (predicate.test(v)) {
            System.out.println(v);
        }
    }

    public void printBigValueAnd(int value, Predicate<Integer> predicate) {
        if (predicate.and(v -> v < 8).test(value)) {
            System.out.println("value < 8:"+value);
        } else {
            System.out.println("value >= 8:"+value);
        }
    }
}
