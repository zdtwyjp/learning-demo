package org.tech.java.module;

import org.junit.Test;

public class ModuleTest {

    @Test
    public void test() {
        Class<ModuleTest> mt = ModuleTest.class;
        Module md = mt.getModule();
        System.out.println("Module name:"+md);
    }
}
