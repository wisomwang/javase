package concurrency.executor.rejectedexecutionhandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

	public static void main(String[] args) {
		RejectedTaskController controller = new RejectedTaskController();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(controller);

		System.out.println("Main: Starting.");

		for (int i = 0; i < 3; i++) {
			Task task = new Task("Task " + i);
			executor.submit(task);
		}

		System.out.println("Main: Shutting down the Executor.");
		/** 会等待正在执行的任务执行完毕，但不会等待处于等待中的任务完成，也就是说处于等待中的任务不会被执行了 */
		executor.shutdown();

		System.out.println("Main: Sending another task.");
		Task task = new Task("Another task");
		executor.submit(task);

		System.out.println("Main: End.");
	}

}
