package org.tech.java.concurrent.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	private static final int THREAD_POOL_SIZE = 2;

	public static void main(String[] args) {
//	        List<Double> list = new ArrayList<Double>();
			List<Double> list = new CopyOnWriteArrayList<Double>();
	        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	        es.execute(new AddThread(list));
	        es.execute(new AddThread(list));
	        es.shutdown();
	    }
}
