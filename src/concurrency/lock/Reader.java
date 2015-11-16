package concurrency.lock;

public class Reader extends Thread {
	private PricesInfo info;

	public Reader(PricesInfo info) {
		this.info = info;
	}

	@Override
	public void run() {
		System.out.println("get price1 " + info.getPrice1());
		System.out.println("get price2 " + info.getPrice2());
	}

}
