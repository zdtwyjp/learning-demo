package org.tech.java.jdk.jdk8.functionalInterface;

import org.junit.Test;

import java.util.function.Function;

/**
 * 测试Function函数式接口
 */
public class FunctionTest1 {

    @Test
    public void test() {
        Function<Integer, Integer> incr1 = x -> x + 1;
        Function<Integer, Integer> multiply = x -> x * 2;
        int x = 3;
        System.out.println("f(x)=x+1,when x="+x+",f(x)="+incr1.apply(x));
        System.out.println("f(x)=x+1,g(x)=2x,when x="+x+",f(g(x))="+incr1.compose(multiply).apply(x));
        System.out.println("f(x)=x+1,g(x)=2x,when x="+x+",g(f(x))="+incr1.andThen(multiply).apply(x));
    }
}
