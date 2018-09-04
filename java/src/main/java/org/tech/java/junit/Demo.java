package org.tech.java.junit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    @Before
    public void init() {
        logger.info("init");
    }

    @Test
    public void test() {
        logger.info("do test");
    }

}
