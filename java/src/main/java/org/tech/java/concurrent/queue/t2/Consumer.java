package org.tech.java.concurrent.queue.t2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<Task> buffer;

	public Consumer(BlockingQueue<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Task task = buffer.take();
				System.out.println("Consumer["
						+ Thread.currentThread().getName() + "] got " + task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
