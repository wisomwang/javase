package concurrency.executor.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();

		/** FutureTask实现RunnableFuture,RunnableFuture继承Runnable和Future */
		ResultTask[] resultTasks = new ResultTask[5];

		for (int i = 0; i < 5; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(executableTask);

			/** submit runnable,FutureTask实现了runnable */
			executor.submit(resultTasks[i]);

		}

		for (int i = 0; i < 5; i++) {

			/**
			 * 该方法调用会失败，如果task已经完成，或者已经被关掉，或者由于某种原因不能被cancel
			 * 当调用该方法时任务还没有开始（等待线程来分配他）,那么成功取消，如果任务已经在运行，取决于
			 * 参数，如果参数为true,那么任务被取消，如果为 false,任务不会被取消
			 */
			resultTasks[i].cancel(true);
		}

		for (int i = 0; i < 5; i++) {
			if (!resultTasks[i].isCancelled()) {
				try {
					System.out.println(resultTasks[i].getName() + "'s result is " + resultTasks[i].get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
