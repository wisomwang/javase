package concurrency.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 周期性
 * 
 * @author smwang
 * 
 */
public class TestPeriodical {

	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		for (int i = 0; i < 1; i++) {
			TaskPeriod task = new TaskPeriod("Task " + i);

			/** 延时i秒后，第一次开始执行，然后间隔4s周期执行 */
			executor.scheduleAtFixedRate(task, 1, 4, TimeUnit.SECONDS);
		}

		System.out.println("Main: End of the execution");
	}

}

class TaskPeriod implements Runnable {
	private String name;

	public TaskPeriod(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Starting at : %s \n", name, new Date());

	}

}
