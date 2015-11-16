package concurrency.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestDelay {

	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task " + i);

			/** 延时i秒执行task */
			executor.schedule(task, i, TimeUnit.SECONDS);
		}

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}

}

class Task implements Callable<String> {
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s \n", name, new Date());

		return "hello world";
	}

}
