package concurrency.syncassit.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

	private List<String> consumerBuffer;
	private Exchanger<List<String>> exchanger;

	public Consumer(List<String> consumerBuffer, Exchanger<List<String>> exchanger) {
		this.consumerBuffer = consumerBuffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 1; i++) {
			System.out.println("Consumer cycle : " + cycle);

			try {
				/** consumerBuffer中的元素来自于producerBuffer */
				consumerBuffer = exchanger.exchange(consumerBuffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Consumer: data size from produer is " + consumerBuffer.size());
			for (int j = 0; j < 10; j++) {
				String message = consumerBuffer.get(j);
				System.out.println("Consumer: data element from producer is " + message);
			}

			consumerBuffer.clear();
			cycle++;
		}
	}

}
