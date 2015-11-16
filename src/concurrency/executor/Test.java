package concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		/** 两个线程执行五个任务 */
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			FactorialCalculator task = new FactorialCalculator(i);
			Future<Integer> result = executor.submit(task);
			resultList.add(result);
		}

		do {
			System.out.println("Completed task count: " + executor.getCompletedTaskCount());

			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.println("Task " + i + " " + result.isDone());
				// System.out.println("Task " + i + " result is " +
				// result.get());
			}
		} while (executor.getCompletedTaskCount() < resultList.size());

		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			System.out.println("Task " + i + " result is " + result.get());
		}
		
		executor.shutdown();
	}
}
