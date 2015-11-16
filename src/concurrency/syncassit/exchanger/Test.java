package concurrency.syncassit.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Test {

	public static void main(String[] args) {
		List<String> produceBuffer = new ArrayList<>();
		List<String> consumerBuffer = new ArrayList<>();
		
		Exchanger<List<String>> exchanger = new Exchanger<>();
		
		Producer producer = new Producer(produceBuffer, exchanger);
		Consumer consumer = new Consumer(consumerBuffer, exchanger);

		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
		
	}

}
