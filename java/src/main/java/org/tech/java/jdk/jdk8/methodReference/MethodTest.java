package org.tech.java.jdk.jdk8.methodReference;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodTest {

    @Test
    public void test() {
        List<String> arr = Arrays.asList("001", "002", "003");
        arr.forEach(System.out::println);
    }

    @Test
    public void test2() {
        Car car = Car.create(Car::new);
        List<Car> cs = Arrays.asList(car);
        cs.forEach(Car::repair);
        cs.forEach(Car::m1);
    }
}

class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void m1(Car car) {
        System.out.println("m1 : "+car.toString());
    }

    public void repair() {
        System.out.println("repair");
    }

}