package org.tech.java.jdk.jdk8.functionalInterface;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperatorTest {

    @Test
    public void test() {
        UnaryOperator<Integer> add = x -> x + 1;
        System.out.println(add.apply(1));

        BinaryOperator<Integer> addxy = (x, y) -> x + y;
        System.out.println(addxy.apply(3, 5));

        BinaryOperator<Integer> min = BinaryOperator.minBy((o1, o2) -> o1 - o2);
        System.out.println(min.apply(100, 200));

        BinaryOperator<Integer> max = BinaryOperator.maxBy((o1, o2) -> o1 - o2);
        System.out.println(max.apply(100, 200));

    }
}
