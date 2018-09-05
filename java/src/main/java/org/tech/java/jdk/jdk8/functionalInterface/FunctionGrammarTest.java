package org.tech.java.jdk.jdk8.functionalInterface;

@FunctionalInterface
public interface FunctionGrammarTest {

    /**
     * 一：编译错误：no target method found
     * 没有方法
     */

    /**
     * 二：编译正常
     * boolean test();
     */

    /**
     * 三：编译错误：Multiple non-overrideing abstract method found
     * boolean test();
     * boolean test2();
     */

    /**
     * 四：编译错误：no target method found
     * @Override
     * boolean equals(Object obj);
     */

    /**
     * 五：编译正常
     * @Override
     * boolean equals(Object obj);
     * void test(Object obj);
     */

    /**
     * 六：编译正常
     * boolean equals(Object obj);
     * String toString();
     * void test(Object obj);
     */

    /**
     * 七：编译正常
     * void test();
     * default void test2(){}
     * default void test3(){}
     */

    void test();

}
