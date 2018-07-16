package org.tech.java.concurrent.queue.t2;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private BlockingQueue<Task> buffer;

	public Producer(BlockingQueue<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Task task = new Task();
				buffer.put(task);
				System.out.println("Producer["
						+ Thread.currentThread().getName() + "] put " + task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
