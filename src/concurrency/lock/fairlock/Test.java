package concurrency.lock.fairlock;

public class Test {

	public static void main(String[] args) {
		PricesInfo info = new PricesInfo(10, 10, true);

		for (int i = 0; i < 10; i++) {
			Thread threadReader = new Reader(info);
			threadReader.start();

			/** 下面这段代码不能少，不然模拟不出效果，这段代码是为了每隔10ms创建一个线程，并处于可执行状态 */
			/** 如果去掉这段代码，貌似可能不能确定线程的创建顺序，从而导致线程被调度的顺序是不可预测的 */
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
