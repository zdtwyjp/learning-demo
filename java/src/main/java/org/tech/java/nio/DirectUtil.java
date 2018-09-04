package org.tech.java.nio;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

public class DirectUtil {
	
	public static void printMemoryLog() {
		try {
            Class c = Class.forName("java.nio.Bits");
            Field field1 = c.getDeclaredField("maxMemory");
            field1.setAccessible(true);
            Field field2 = c.getDeclaredField("reservedMemory");
            field2.setAccessible(true);
            Field field3 = c.getDeclaredField("totalCapacity");
            field3.setAccessible(true);
            Field field4 = c.getDeclaredField("count");
            field4.setAccessible(true);
            
            synchronized (c) {
            	System.out.println(Thread.currentThread().getName()+"----------------------------- begin ");
                Long max =  (Long) field1.get(null);
                System.out.println(Thread.currentThread().getName()+"max="+max+" "+(max/(1024*1024))+"mb");
                AtomicLong reserve = (AtomicLong) field2.get(null);
                System.out.println(Thread.currentThread().getName()+"reserve="+reserve);
                AtomicLong totalCapacity = (AtomicLong) field3.get(null);
                System.out.println(Thread.currentThread().getName()+"totalCapacity="+totalCapacity);
                AtomicLong count = (AtomicLong) field4.get(null);
                System.out.println(Thread.currentThread().getName()+"count="+count);
                System.out.println(Thread.currentThread().getName()+"----------------------------- end ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}

}
