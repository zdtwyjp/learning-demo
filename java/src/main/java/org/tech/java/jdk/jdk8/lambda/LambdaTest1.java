package org.tech.java.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class LambdaTest1 {

    @Test
    public void t1() {
        // 使用匿名内部类
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });
        t.start();
        // 使用Lambda表达式
        Thread t2 = new Thread(() -> System.out.println("Hello World"));
        t2.start();
    }

    @Test
    public void t2() {
        // 使用匿名内部类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        r.run();
        // 使用Lambda表达式
        Runnable t2 = () -> System.out.println("Hello World");
        t2.run();
    }

    @Test
    public void t3() {
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        // 使用匿名内部类排序
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        });
        // 使用Lambda表达式排序
        Arrays.sort(players, (s1, s2) -> s1.compareTo(s2));
    }

}
