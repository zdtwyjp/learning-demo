package org.tech.java.jdk.jdk8.defaultMethod;

import org.junit.Test;

public class DefaultMethodTest {

    @Test
    public void test() {
        Car car = new Car();
        car.print();
    }

}

/**
 * 单个默认方法
 */
interface Vehicle {
    default void print() {
        System.out.println("Test");
    }

    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("Test");
    }

    default void print2() {
        System.out.println("Test2");
    }
}

class Car implements Vehicle, FourWheeler {
    @Override
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆四轮汽车!");
    }
}