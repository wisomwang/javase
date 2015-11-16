package concurrency.lock;

public class Test {

	public static void main(String[] args) {
		PricesInfo info = new PricesInfo(10, 10);

		Thread threadReader = new Reader(info);
		Thread threadWriter = new Thread(new Writer(info));

		threadReader.start();
		threadWriter.start();
	}

}
