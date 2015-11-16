package concurrency.forkjoin.demo3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * 验证共享ForkJoinPool时，子任务会不会被被他线程的任务窃取
 */
class PrintTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;

	// 每个"小任务"最多只对5个数求和
	private static final int MAX = 5;

	private int start;
	private int end;

	PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if ((end - start) < MAX) {
			int result = 0;
			for (int i = start; i < end; i++) {
				result += i;
			}
			System.out.println("sum=" + result);
			return result;
		} else {
			// 将大任务分解成两个小任务
			int middle = (start + end) / 2;
			PrintTask left = new PrintTask(start, middle);
			PrintTask right = new PrintTask(middle, end);
			// 并行执行两个小任务
			/** 把子任务加入到当前线程所对应的队列中或者把子任务加入到新创建的一个工作者线程中对应的队列中 */
			left.fork();
			right.fork();

			return left.join() + right.join();
		}
	}
}

public class ForkJoinPoolTest {
	static ForkJoinPool forkJoinPool = new ForkJoinPool();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		RunProc proc1 = new RunProc(forkJoinPool, new PrintTask(0, 100));
		Thread thread1 = new Thread(proc1);

		RunProc proc2 = new RunProc(forkJoinPool, new PrintTask(0, 500));
		Thread thread2 = new Thread(proc2);

		thread1.start();
		thread2.start();

	}

}

class RunProc implements Runnable {
	private ForkJoinPool forkJoinPool;
	PrintTask task;

	public RunProc(ForkJoinPool forkJoinPool, PrintTask task) {
		this.forkJoinPool = forkJoinPool;
		this.task = task;
	}

	@Override
	public void run() {
		/** forkJoinPool可以作为共享变量， 不同的task之间不会窃取对方的子任务 */
		Integer result = forkJoinPool.invoke(task);
		System.out.println("result=" + result);

		// ForkJoinTask<Integer> result = forkJoinPool.submit(task);
		// try {
		// System.out.println("result=" + result.get());
		// } catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
