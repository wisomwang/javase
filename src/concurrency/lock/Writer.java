package concurrency.lock;

public class Writer implements Runnable {
	private PricesInfo info;

	public Writer(PricesInfo info) {
		this.info = info;
	}

	@Override
	public void run() {

		System.out.println("update price to 20, 20 ");

		/** 有可能会出现这里已经更新完了，但另外的线程取到的还是旧的值,因为在写的时候是不影响读的 */
		info.updatePrice(20, 20);
		System.out.println("get price1 after update " + info.getPrice1());
		System.out.println("get price2 after update " + info.getPrice2());
	}
}
