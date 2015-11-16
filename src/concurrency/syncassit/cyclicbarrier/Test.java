package concurrency.syncassit.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Test {
	private static final int ROWS = 10000;
	private static final int LINES_EACHSEARCH = 2000;
	private static final int SEARCH_THREADS = 5;
	private static final int COLUMN = 10;

	public static void main(String[] args) {

		Result result = new Result(ROWS);
		Group group = new Group(result);

		/** 当所有线程都到达集中点后，会启动新线程执行group task */
		CyclicBarrier barrier = new CyclicBarrier(SEARCH_THREADS, group);

		Matrix matrix = new Matrix(ROWS, COLUMN);
		Thread[] threads = new Thread[SEARCH_THREADS];
		for (int i = 0; i < SEARCH_THREADS; i++) {
			threads[i] = new Thread(new Search(i * LINES_EACHSEARCH, (i + 1) * LINES_EACHSEARCH, matrix, result, 3,
					barrier));
		}

		for (int i = 0; i < SEARCH_THREADS; i++) {
			threads[i].start();
		}

//		barrier.reset();
	}
}
