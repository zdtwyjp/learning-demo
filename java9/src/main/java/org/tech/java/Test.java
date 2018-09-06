package org.tech.java;

public class Test {
    public static void main(String[] args) {
        System.out.println(3);
        int major = Runtime.version().major();
        System.out.println(major);
        System.out.println(System.getProperty("java.runtime.version"));
    }
}
