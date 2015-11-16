package concurrency.syncassit.cyclicbarrier;

/**
 * 统计结果，对每行中的数目进行最终累加
 * 
 * @author smwang
 * 
 */
public class Group implements Runnable {
	private Result result;

	public Group(Result result) {
		this.result = result;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.println("Grouper: Processing results...");

		for (int count : result.getData()) {
			finalResult += count;
		}
		System.out.println("Grouper: Total result: " + finalResult);
	}

}
