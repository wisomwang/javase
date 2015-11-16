package concurrency.syncassit.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PrintQueue {
	private final Semaphore semaphore;

	public PrintQueue() {
		this.semaphore = new Semaphore(1);
	}

	public void print() {
		try {
			semaphore.acquire();
			System.out.println("开始打印，耗时2s");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

}
