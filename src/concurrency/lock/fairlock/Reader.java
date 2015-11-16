package concurrency.lock.fairlock;

public class Reader extends Thread {
	private PricesInfo info;

	public Reader(PricesInfo info) {
		this.info = info;
	}

	@Override
	public void run() {
		info.getPrice1();
	}

}
