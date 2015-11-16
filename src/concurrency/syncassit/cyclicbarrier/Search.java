package concurrency.syncassit.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Search implements Runnable {

	private int firstRow;
	private int lastRow;
	private Matrix matrix;
	private Result result;
	private int searchNumber;
	private CyclicBarrier barrier;

	public Search(int firstRow, int lastRow, Matrix matrix, Result result, int searchNumber, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.matrix = matrix;
		this.result = result;
		this.searchNumber = searchNumber;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		System.out.println("process lines from " + firstRow + " to " + lastRow);

		for (int i = firstRow; i < lastRow; i++) {
			int rowData[] = matrix.getRow(i);
			int counter = 0;
			for (int j = 0; j < rowData.length; j++) {
				if (searchNumber == rowData[j]) {
					counter++;
				}
			}
			result.setData(i, counter);
		}

		System.out.println("lines processed");

		try {
			/**
			 * 每个线程到达这里都会阻塞休眠并等待其他到达，当最后一个线程到达这里后，cyclicBarrier对象会唤醒所有在在等待的线程，
			 * 然后这些线程继续执行
			 */
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
