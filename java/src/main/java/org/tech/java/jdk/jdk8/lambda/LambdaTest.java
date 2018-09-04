package org.tech.java.jdk.jdk8.lambda;

import org.junit.Test;

public class LambdaTest {

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String msg);
    }

    private int operate(int a, int b, MathOperation mo) {
        return mo.operation(a, b);
    }

    @Test
    public void test() {
        // 类型声明
        MathOperation addition = (a, b) -> a + b;
        System.out.println("10 + 5 = "+operate(10, 5, addition));
        System.out.println("10 + 5 = "+operate(10, 5, (a, b) -> a - b));
        System.out.println("10 + 5 = "+operate(10, 5, (a, b) -> a * b));

        GreetingService gs1 = message -> System.out.println("Hello:"+message);
        gs1.sayMessage("Test");
    }


}
