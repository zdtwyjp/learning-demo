package org.tech.java;

import org.junit.Test;

public class VersionTest {

    @Test
    public void test() {
        Runtime.Version v = Runtime.version();
        System.out.println(v.major());
        System.out.println(v.minor());
        System.out.println(v.security());
        System.out.println(v.pre());
        System.out.println(v.build().get());
    }
}
