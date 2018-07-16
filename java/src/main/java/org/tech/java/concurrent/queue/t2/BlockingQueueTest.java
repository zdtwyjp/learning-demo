package org.tech.java.concurrent.queue.t2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
	        BlockingQueue<Task> buffer = new LinkedBlockingQueue<Task>(Constants.MAX_BUFFER_SIZE);
	        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
	        for(int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
	            es.execute(new Producer(buffer));
	        }
	        for(int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
	            es.execute(new Consumer(buffer));
	        }
	    }
}

