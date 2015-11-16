package concurrency.forkjoin.demo1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 
 * RecursiveAction代表没有返回值的任务。
 */
class PrintTask extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	// 每个"小任务"最多只打印20个数
	private static final int MAX = 20;

	private int start;
	private int end;

	PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		// 当end-start的值小于MAX时候，开始打印
		if ((end - start) < MAX) {
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + "的i值:" + i);
			}
		} else {
			// 将大任务分解成两个小任务
			int middle = (start + end) / 2;
			PrintTask left = new PrintTask(start, middle);
			PrintTask right = new PrintTask(middle, end);
			// 并行执行两个小任务
			/** 把子任务加入到当前线程所对应的队列中或者把子任务加入到新创建的一个工作者线程中对应的队列中 */
			left.fork();
			right.fork();
		}
	}
}

public class ForkJoinPoolTest {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// 提交可分解的PrintTask任务
		forkJoinPool.submit(new PrintTask(0, 100));
//
//		// 关闭线程池
		forkJoinPool.shutdown();
//
		forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);// 阻塞当前线程直到
															// ForkJoinPool
															// 中所有的任务都执行结束
	}

}
